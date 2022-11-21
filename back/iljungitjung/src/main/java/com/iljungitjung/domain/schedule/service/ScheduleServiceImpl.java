package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.dto.CategoryViewResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.dto.schedule.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public ScheduleViewResponseDto scheduleView(String nickname, String startDate, String endDate, HttpSession httpSession) {

        User userFrom = userService.findUserBySessionId(httpSession);

        User userTo = userRepository.findUserByNickname(nickname).orElseThrow(()->{
            throw new NoExistUserException();
        });

        return makeScheduleViewResponseDto(userFrom, userTo, startDate, endDate);
    }

    private ScheduleViewResponseDto makeScheduleViewResponseDto(User userFrom, User userTo, String startDate, String endDate){

        boolean viewMySchedule = checkSameUser(userFrom, userTo);
        boolean validDate = validDateCheck(startDate, endDate);

        startDate+="0000";
        endDate+="2359";

        Date startDateFormat = makeDateFormat(validDate, startDate);
        Date endDateFormat = makeDateFormat(validDate, endDate);

        return divideScheduleByType(userTo, viewMySchedule, validDate, startDateFormat, endDateFormat);
    }

    private ScheduleViewResponseDto divideScheduleByType(User userTo, boolean viewMySchedule, boolean validDate, Date startDateFormat, Date endDateFormat){
        List<Schedule> scheduleList = scheduleRepository.findByUserTo_IdIs(userTo.getId());

        List<ScheduleViewDto> requestList = new ArrayList<>();
        List<ScheduleViewDto> acceptList = new ArrayList<>();
        List<ScheduleBlockDto> blockList = new ArrayList<>();
        List<ScheduleCancelDto> cancelList = new ArrayList<>();
        List<CategoryViewResponseDto> categoryList = new ArrayList<>();

        List<Boolean> blockDayList = userTo.getBlockDays();

        scheduleList.forEach(schedule -> {
            if(validDate && checkDate(schedule, startDateFormat, endDateFormat)) return;

            if(viewMySchedule){
                if(schedule.getType().equals(Type.CANCEL)) cancelList.add(new ScheduleCancelDto(schedule));
            }
            if(schedule.getType().equals(Type.REQUEST)) requestList.add(new ScheduleViewDto(schedule));
            if (schedule.getType().equals(Type.ACCEPT)) acceptList.add(new ScheduleViewDto(schedule));
            if (schedule.getType().equals(Type.BLOCK)) blockList.add(new ScheduleBlockDto(schedule));
        });

        userTo.getCategoryList().forEach(category -> categoryList.add(new CategoryViewResponseDto(category)));

        return new ScheduleViewResponseDto(categoryList,
                requestList,
                acceptList,
                blockList,
                cancelList,
                blockDayList);
    }

    private boolean checkDate(Schedule schedule, Date startDateFormat, Date endDateFormat){
        return schedule.getStartDate().before(startDateFormat)
                || schedule.getEndDate().before(startDateFormat)
                || schedule.getStartDate().after(endDateFormat)
                || schedule.getEndDate().after(endDateFormat);
    }

    private boolean checkSameUser(User userFrom, User userTo){
        return userFrom.getId()==userTo.getId();
    }

    private boolean validDateCheck(String startDate, String endDate){
        return StringUtils.hasText(startDate) && StringUtils.hasText(endDate);
    }

    private Date makeDateFormat(boolean validDate, String date){
        Date dateFormat = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");

        if(validDate){
            try{
                dateFormat = formatter.parse(date);
            }catch (Exception e){
                throw new DateFormatErrorException();
            }
        }

        return dateFormat;
    }

    @Override
    public ScheduleViewDetailResponseDto scheduleViewDetail(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });
        return new ScheduleViewDetailResponseDto(schedule);
    }
}
