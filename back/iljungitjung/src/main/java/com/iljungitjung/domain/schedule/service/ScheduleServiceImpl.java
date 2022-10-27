package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.schedule.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.Users;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    @Override
    public ScheduleViewResponseDto scheduleView(String nickname, ScheduleViewRequestDto scheduleViewRequestDto) {

        //닉네임으로 유저 조회
        ScheduleViewResponseDto responseDtos;

        Date startDate;
        Date endDate;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDate = formatter.parse(scheduleViewRequestDto.getStartDate()+"0000");
            endDate = formatter.parse(scheduleViewRequestDto.getEndDate()+"2359");
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        try{
            Users user = userRepository.findUsersByNickname(nickname).orElseThrow(() -> {
                throw new NoExistUserException();
            });
            List<Schedule> scheduleList = user.getScheduleRequestList();
            scheduleList.addAll(user.getScheduleResponseList());

            List<ScheduleViewDto> requestList = new ArrayList<>();
            List<ScheduleViewDto> acceptList = new ArrayList<>();
            List<ScheduleBlockDto> blockList = new ArrayList<>();
            List<ScheduleCancelDto> cancelList = new ArrayList<>();
            for(Schedule schedule : scheduleList){
                if(schedule.getStartDate().before(startDate) || schedule.getEndDate().after(endDate)) continue;
                if(schedule.getType().equals(Type.REQUEST)){
                    requestList.add(new ScheduleViewDto(schedule));
                }else if(schedule.getType().equals(Type.ACCEPT)){
                    acceptList.add(new ScheduleViewDto(schedule));
                }else if(schedule.getType().equals(Type.BLOCK)){
                    blockList.add(new ScheduleBlockDto(schedule));
                }else{
                    cancelList.add(new ScheduleCancelDto(schedule));
                }
            }
            responseDtos = new ScheduleViewResponseDto(requestList, acceptList, blockList, cancelList);
        }catch (Exception e){
            throw new NoExistScheduleException();
        }

        return responseDtos;
    }

    @Override
    public ScheduleViewDetailResponseDto scheduleViewDetail(Long id) {
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });
        ScheduleViewDetailResponseDto responseDto = new ScheduleViewDetailResponseDto(schedule);
        return responseDto;
    }

}
