package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.schedule.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ScheduleViewResponseDto scheduleView(String nickname) {

        //닉네임으로 유저 조회
        String id = "1";
        ScheduleViewResponseDto responseDtos;

        try{
            List<Schedule> scheduleList = scheduleRepository.findScheduleByUserFromId(id);
            List<ScheduleViewDto> requestList = new ArrayList<>();
            List<ScheduleViewDto> acceptList = new ArrayList<>();
            List<ScheduleBlockDto> blockList = new ArrayList<>();
            List<ScheduleCancelDto> cancelList = new ArrayList<>();
            for(Schedule schedule : scheduleList){
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