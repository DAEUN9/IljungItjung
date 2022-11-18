package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.dto.reservation.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.*;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto, HttpSession httpSession) {

        User userFrom = userService.findUserBySessionId(httpSession);

        User userTo= userRepository.findUserByNickname(reservationRequestDto.getUserToNickname()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

        Schedule schedule = saveSchedule(userFrom, userTo, reservationRequestDto);

        notificationService.autoReservationMessage(schedule);

        return new ReservationIdResponseDto(schedule.getId());
    }
    private Schedule saveSchedule(User userFrom, User userTo, ReservationRequestDto reservationRequestDto){

        Category category = categoryRepository.findByCategoryNameAndUser_Email(reservationRequestDto.getCategoryName(), userTo.getEmail()).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        Date startDate = makeDateFormat(reservationRequestDto.getDate()+reservationRequestDto.getStartTime());
        Date endDate = calcEndDate(startDate, category);

        Schedule schedule = reservationRequestDto.toEntity(startDate, endDate, category.getColor());
        schedule.setScheduleRequestList(userFrom);
        schedule.setScheduleResponseList(userTo);

        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    private Date calcEndDate(Date startDate, Category category){
        String date = category.getTime();
        Calendar cal = Calendar.getInstance();

        cal.setTime(startDate);
        cal.add(Calendar.MINUTE, Integer.parseInt(date.substring(2)));
        cal.add(Calendar.HOUR, Integer.parseInt(date.substring(0, 2)));

        Date endDate = cal.getTime();

        return endDate;
    }
    @Override
    @Transactional
    public ReservationIdResponseDto reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });

        UserManageReservation(user, schedule, reservationManageRequestDto);

        notificationService.autoReservationMessage(schedule);

        return new ReservationIdResponseDto(schedule.getId());
    }

    private void UserManageReservation(User user, Schedule schedule, ReservationManageRequestDto reservationManageRequestDto){
        if(checkSameUser(user, schedule.getUserTo())) providerManageReservation(schedule, reservationManageRequestDto);
        else if(checkSameUser(user, schedule.getUserFrom())) applicantManageReservation(schedule, reservationManageRequestDto);
    }
    private boolean checkSameUser(User userFrom, User userTo){
        return userFrom.getId()==userTo.getId();
    }
    private void providerManageReservation(Schedule schedule, ReservationManageRequestDto reservationManageRequestDto){
        if(reservationManageRequestDto.isAccept()){
            schedule.accepted();
            return;
        }
        schedule.canceled("제공자", reservationManageRequestDto.getReason());
    }

    private void applicantManageReservation(Schedule schedule, ReservationManageRequestDto reservationManageRequestDto){
        if(reservationManageRequestDto.isAccept()){
            throw new NoGrantAcceptScheduleException();
        }
        schedule.canceled("사용자", reservationManageRequestDto.getReason());
    }

    @Override
    @Transactional
    public void reservationDelete(Long id, String reason, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleException();
        });

        checkUserSchedule(user, schedule);

        notificationService.autoReservationMessage(schedule);

        scheduleRepository.delete(schedule);
    }

    private void checkUserSchedule(User user, Schedule schedule){
        if (schedule.isUserSchedule(user)) {
            schedule.deleted();
            return;
        }
        throw new NoGrantDeleteScheduleException();
    }

    @Override
    @Transactional
    public ReservationBlockResponseDto reservationBlock(ReservationBlockListRequestDto reservationBlockListRequestDto, HttpSession httpSession) {
        User user = userService.findUserBySessionId(httpSession);

        updateBlockDays(user, reservationBlockListRequestDto);

        return makeReservationBlockResponseDto(user, reservationBlockListRequestDto);
    }
    private ReservationBlockResponseDto makeReservationBlockResponseDto(User user, ReservationBlockListRequestDto reservationBlockListRequestDto){
        Long blockReservationCount=0L;

        for(ReservationBlockDto reservationBlockDto : reservationBlockListRequestDto.getBlockList()){
            saveBlockReservation(user, reservationBlockDto);
            blockReservationCount++;
        }

        return new ReservationBlockResponseDto(blockReservationCount);
    }
    private void saveBlockReservation(User user, ReservationBlockDto reservationBlockDto){
        Date startDateFormat = makeDateFormat(reservationBlockDto.getDate()+reservationBlockDto.getStartTime());
        Date endDateFormat = makeDateFormat(reservationBlockDto.getDate()+reservationBlockDto.getEndTime());

        Schedule schedule = reservationBlockDto.toEntity(startDateFormat, endDateFormat);
        schedule.blocked();
        schedule.setScheduleResponseList(user);
        scheduleRepository.save(schedule);
    }

    private void updateBlockDays(User user, ReservationBlockListRequestDto reservationBlockListRequestDto){
        List<Schedule> scheduleList = scheduleRepository.findByUserTo_IdIs(user.getId());

        scheduleList.forEach(schedule -> {
            if(schedule.getType().equals(Type.BLOCK)) scheduleRepository.delete(schedule);
        });

        user.updateBlockDays(reservationBlockListRequestDto.getDays());
    }

    @Override
    public ReservationViewResponseDto reservationView(String startDate, String endDate, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        return makeReservationViewResponseDto(user, startDate, endDate);
    }

    private ReservationViewResponseDto makeReservationViewResponseDto(User user, String startDate, String endDate){

        startDate += "0000";
        endDate += "2359";

        Date startDateFormat = makeDateFormat(startDate);
        Date endDateFormat = makeDateFormat(endDate);

        return divideScheduleByType(user, startDateFormat, endDateFormat);
    }

    private ReservationViewResponseDto divideScheduleByType(User user, Date startDateFormat, Date endDateFormat){
        List<Schedule> scheduleList = scheduleRepository.findByUserFrom_IdIs(user.getId());

        List<ReservationViewDto> reservationViewDtoList = new ArrayList<>();
        List<ReservationCancelViewDto> reservationCancelViewDtoList = new ArrayList<>();

        scheduleList.forEach(schedule -> {
            if(checkDate(schedule, startDateFormat, endDateFormat)) return;

            if(schedule.getType().equals(Type.BLOCK)) return;
            if(schedule.getType().equals(Type.DELETE)) return;

            if(schedule.getType().equals(Type.CANCEL)) reservationCancelViewDtoList.add(new ReservationCancelViewDto(schedule));
            if(schedule.getType().equals(Type.REQUEST) || schedule.getType().equals(Type.ACCEPT)) reservationViewDtoList.add(new ReservationViewDto(schedule));
        });

        return new ReservationViewResponseDto(reservationViewDtoList, reservationCancelViewDtoList);
    }

    private boolean checkDate(Schedule schedule, Date startDateFormat, Date endDateFormat){
        return schedule.getStartDate().before(startDateFormat)
                || schedule.getEndDate().before(startDateFormat)
                || schedule.getStartDate().after(endDateFormat)
                || schedule.getEndDate().after(endDateFormat);
    }

    private Date makeDateFormat(String date){
        Date dateFormat;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");

        try{
            dateFormat = formatter.parse(date);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        return dateFormat;
    }
}
