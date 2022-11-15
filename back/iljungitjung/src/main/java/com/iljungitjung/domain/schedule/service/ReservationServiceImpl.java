package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantDeleteCategoryException;
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
import com.iljungitjung.global.login.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;
    private final UserService userService;
    private final NotificationService notificasionService;

    @Override
    @Transactional
    public ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        User userTo= userRepository.findUserByNickname(reservationRequestDto.getUserToNickname()).orElseThrow(() -> {
            throw new NoExistUserException();
        });

        Category category = categoryRepository.findByCategoryNameAndUser_Email(reservationRequestDto.getCategoryName(), user.getEmail()).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        String date = category.getTime();
        Calendar cal = Calendar.getInstance();

        try{
            startDate = formatter.parse(reservationRequestDto.getDate()+reservationRequestDto.getStartTime());
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        cal.setTime(startDate);
        cal.add(Calendar.MINUTE, Integer.parseInt(date.substring(2)));
        cal.add(Calendar.HOUR, Integer.parseInt(date.substring(0, 2)));

        Date endDate = cal.getTime();

        Schedule schedule = reservationRequestDto.toEntity(startDate, endDate, category.getColor());
        schedule.setScheduleRequestList(user);
        schedule.setScheduleResponseList(userTo);

        schedule = scheduleRepository.save(schedule);
        notificasionService.autoReservationMessage(schedule);
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
                schedule.accpeted();
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
        notificasionService.autoReservationMessage(schedule);
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    public void reservationDelete(Long id, String reason, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleException();
        });

        if(!checkSamePerson(user, schedule.getUserTo())) throw new NoGrantDeleteScheduleException();

        scheduleRepository.delete(schedule);
        schedule.deleted();
        notificasionService.autoReservationMessage(schedule);
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto, HttpSession httpSession) {
        User user = userService.findUserBySessionId(httpSession);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        Date endDate;
        try{
            startDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getStartTime());
            endDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getEndTime());
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        Schedule schedule = reservationBlockRequestDto.toEntity(startDate, endDate);
        schedule.setScheduleResponseList(user);
        schedule = scheduleRepository.save(schedule);

        return new ReservationIdResponseDto(schedule.getId());

    }

    @Override
    public ReservationViewResponseDto reservationView(String startDate, String endDate, HttpSession httpSession) {

        User user = userService.findUserBySessionId(httpSession);

        Date startDateFormat;
        Date endDateFormat;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDateFormat = formatter.parse(startDate+"0000");
            endDateFormat = formatter.parse(endDate+"2359");
        }catch (Exception e){
            throw new DateFormatErrorException();
        }


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
        if(schedule.getStartDate().before(startDateFormat) || schedule.getEndDate().before(startDateFormat) || schedule.getStartDate().after(endDateFormat) || schedule.getEndDate().after(endDateFormat)) return true;
        return false;
    }
    public boolean checkSamePerson(User user1, User user2){
        if(user1.getId()==user2.getId()) return true;
        return false;
    }
}

