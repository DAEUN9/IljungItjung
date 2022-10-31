package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.dto.CategoryViewResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.schedule.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
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
        User user = userRepository.findUserByNickname(nickname).get();
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
            List<Schedule> scheduleList = scheduleRepository.findByUserFrom_NicknameIs(nickname);
            List<ScheduleViewDto> requestList = new ArrayList<>();
            List<ScheduleViewDto> acceptList = new ArrayList<>();
            List<ScheduleBlockDto> blockList = new ArrayList<>();
            List<ScheduleCancelDto> cancelList = new ArrayList<>();


            if(scheduleViewRequestDto.isMyView()){
                for(Schedule schedule : scheduleList){
                    if(schedule.getStartDate().before(startDate) || schedule.getEndDate().after(endDate)) continue;
                    if(schedule.getType().equals(Type.ACCEPT)){
                        acceptList.add(new ScheduleViewDto(schedule));
                    }else if(schedule.getType().equals(Type.BLOCK)){
                        blockList.add(new ScheduleBlockDto(schedule));
                    }
                }
            }
            else{
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
            }
            List<CategoryViewResponseDto> categoryList = new ArrayList<>();
            for(Category category :user.getCategoryList()){
                categoryList.add(new CategoryViewResponseDto(category));
            }
            responseDtos = new ScheduleViewResponseDto(categoryList, requestList, acceptList, blockList, cancelList);
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
