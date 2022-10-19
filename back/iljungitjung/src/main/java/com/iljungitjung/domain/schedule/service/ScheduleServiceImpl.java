package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
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

    @Override
    public List<ScheduleViewResponseDto> scheduleView(String nickname) {

        //닉네임으로 유저 조회
        String id = "1";
        String color = "#000000";
        List<ScheduleViewResponseDto> responseDtos = new ArrayList<>();

        try{
            List<Schedule> scheduleList = scheduleRepository.findScheduleByUserToId(id);

            for(Schedule schedule : scheduleList){
                responseDtos.add(new ScheduleViewResponseDto(schedule, color));
            }

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
