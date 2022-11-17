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

        Category category = categoryRepository.findByCategoryNameAndUser_Email(reservationRequestDto.getCategoryName(), userTo.getEmail()).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        Date startDate = makeDateFormat(reservationRequestDto.getDate()+reservationRequestDto.getStartTime());

        String date = category.getTime();
        int minute = Integer.parseInt(date.substring(2));
        int hour = Integer.parseInt(date.substring(0, 2));

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.HOUR, hour);

        Date endDate = cal.getTime();

        Schedule schedule = reservationRequestDto.toEntity(startDate, endDate, category.getColor());
        schedule.setScheduleRequestList(userFrom);
        schedule.setScheduleResponseList(userTo);

        schedule = scheduleRepository.save(schedule);
        //notificasionService.autoReservationMessage(schedule);
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });

        String cancelFrom = "";

        if(checkSamePerson(user, schedule.getUserTo())){
            if(reservationManageRequestDto.isAccept()){
                schedule.accepted();
            }else{
                cancelFrom="제공자";
                schedule.canceled(cancelFrom, reservationManageRequestDto.getReason());
            }
        }else if(checkSamePerson(user, schedule.getUserFrom())){
            if(reservationManageRequestDto.isAccept()){
                throw new NoGrantAcceptScheduleException();
            }else{
                cancelFrom="사용자";
                schedule.canceled(cancelFrom, reservationManageRequestDto.getReason());
            }
        }else{
            throw new NoGrantAccessScheduleException();
        }
        //notificasionService.autoReservationMessage(schedule);
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    public void reservationDelete(Long id, String reason, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleException();
        });

        if(!checkSamePerson(user, schedule.getUserTo())) throw new NoGrantDeleteScheduleException();

        schedule.deleted();
        //notificasionService.autoReservationMessage(schedule);
    }

    @Override
    @Transactional
    public ReservationBlockResponseDto reservationBlock(ReservationBlockListRequestDto reservationBlockListRequestDto, HttpSession httpSession) {
        Long count = 0L;
        User user = userService.findUserBySessionId(httpSession);

        List<Schedule> scheduleList = scheduleRepository.findByUserTo_IdIs(user.getId());

        for(Schedule schedule : scheduleList){

            if(schedule.getType().equals(Type.BLOCK)){
                scheduleRepository.delete(schedule);
            }
        }

        user.updateBlockDays(reservationBlockListRequestDto.getDays());

        for(ReservationBlockDto reservationBlockDto : reservationBlockListRequestDto.getBlockList()){

            Date startDateFormat = makeDateFormat(reservationBlockDto.getDate()+reservationBlockDto.getStartTime());
            Date endDateFormat = makeDateFormat(reservationBlockDto.getDate()+reservationBlockDto.getEndTime());

            Schedule schedule = reservationBlockDto.toEntity(startDateFormat, endDateFormat);
            schedule.setScheduleResponseList(user);
            scheduleRepository.save(schedule);

            count++;
        }
        return new ReservationBlockResponseDto(count);

    }

    @Override
    public ReservationViewResponseDto reservationView(String startDate, String endDate, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        startDate += "0000";
        endDate += "2359";

        Date startDateFormat = makeDateFormat(startDate);
        Date endDateFormat = makeDateFormat(endDate);

        List<Schedule> scheduleList = scheduleRepository.findByUserFrom_IdIs(user.getId());

        List<ReservationViewDto> requestList = new ArrayList<>();
        List<ReservationViewDto> acceptList = new ArrayList<>();
        List<ReservationCancelViewDto> cancelList = new ArrayList<>();

        for(Schedule schedule : scheduleList){
            if(checkDate(schedule, startDateFormat, endDateFormat)) continue;

            if(schedule.getType().equals(Type.REQUEST)){
                requestList.add(new ReservationViewDto(schedule));
            }else if(schedule.getType().equals(Type.ACCEPT)){
                acceptList.add(new ReservationViewDto(schedule));
            }else if(schedule.getType().equals(Type.CANCEL)){
                cancelList.add(new ReservationCancelViewDto(schedule));
            }
        }
        ReservationViewResponseDto responseDtos = new ReservationViewResponseDto(requestList, acceptList, cancelList);


        return responseDtos;
    }
    public boolean checkDate(Schedule schedule, Date startDateFormat, Date endDateFormat){
        return schedule.getStartDate().before(startDateFormat)
                || schedule.getEndDate().before(startDateFormat)
                || schedule.getStartDate().after(endDateFormat)
                || schedule.getEndDate().after(endDateFormat);
    }

    public boolean checkSamePerson(User userFrom, User userTo){
        return userFrom.getId()==userTo.getId();
    }

    public Date makeDateFormat(String date){
        Date dateFormat = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");

        try{
            dateFormat = formatter.parse(date);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        return dateFormat;
    }
}
