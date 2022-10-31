package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.reservation.*;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleBlockDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleCancelDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional
    public ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto) {
        Category category = categoryRepository.findByCategoryName(reservationRequestDto.getCategoryName()).orElseThrow(() -> {
            throw new NoExistCategoryException();
        });
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        try{
            startDate = formatter.parse(reservationRequestDto.getDate()+reservationRequestDto.getStartTime());
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        String date = category.getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MINUTE, Integer.parseInt(date.substring(2)));
        cal.add(Calendar.HOUR, Integer.parseInt(date.substring(0, 2)));

        Date endDate = cal.getTime();
        User userFrom = userRepository.findUserByNickname(reservationRequestDto.getUserFromNickname()).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        User userTo= userRepository.findUserByNickname(reservationRequestDto.getUserToNickname()).orElseThrow(() -> {
            throw new NoExistUserException();
        });
        Schedule schedule = reservationRequestDto.toScheduleEntity(reservationRequestDto, startDate, endDate, category.getColor(), Type.REQUEST);
        schedule.setScheduleRequestList(userTo);
        schedule.setScheduleResponseList(userFrom);
        schedule = scheduleRepository.save(schedule);
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationManage(Long id, String nickname, ReservationManageRequestDto reservationManageRequestDto) {
        //현재 아이디가 해당 예약의 주인일 때만 가능 추가 필수
        /*

         */
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });
        if(reservationManageRequestDto.isAccept()){
            schedule.accpeted();
        }else{
            String cancelFrom = "";
            if(nickname.equals(schedule.getUserFrom().getNickname()))
                cancelFrom="사용자";
            else
                cancelFrom="제공자";
            schedule.canceled(cancelFrom, reservationManageRequestDto.getReason());
        }
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto) {
        //현재 아이디가 해당 예약의 주인일 때만 가능 추가 필수
        /*

        */


        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        Date endDate;
        try{
            startDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getStartTime());
            endDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getEndTime());
        }catch (Exception e){
            throw new DateFormatErrorException();
        }
        User user = userRepository.findUserByNickname(reservationBlockRequestDto.getUserFromNickname()).get();

        Schedule schedule = reservationBlockRequestDto.toScheduleEntity(reservationBlockRequestDto, startDate, endDate);
        schedule.setScheduleResponseList(user);
        schedule = scheduleRepository.save(schedule);
        return new ReservationIdResponseDto(schedule.getId());

    }

    @Override
    public ReservationViewResponseDto reservationView(String nickname, ReservationViewRequestDto reservationViewRequestDto) {
        //닉네임으로 유저 조회
        ReservationViewResponseDto responseDtos;

        Date startDate;
        Date endDate;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDate = formatter.parse(reservationViewRequestDto.getStartDate()+"0000");
            endDate = formatter.parse(reservationViewRequestDto.getEndDate()+"2359");
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        try{
            List<Schedule> scheduleList;
            scheduleList = scheduleRepository.findByUserFrom_NicknameIs(nickname);

            List<ReservationViewDto> requestList = new ArrayList<>();
            List<ReservationViewDto> acceptList = new ArrayList<>();
            List<ReservationCancelViewDto> cancelList = new ArrayList<>();
            for(Schedule schedule : scheduleList){
                if(schedule.getStartDate().before(startDate) || schedule.getEndDate().after(endDate)) continue;
                if(schedule.getType().equals(Type.REQUEST)){
                    requestList.add(new ReservationViewDto(schedule));
                }else if(schedule.getType().equals(Type.ACCEPT)){
                    acceptList.add(new ReservationViewDto(schedule));
                }else if(schedule.getId().equals(Type.CANCEL)){
                    cancelList.add(new ReservationCancelViewDto(schedule));
                }
            }
            responseDtos = new ReservationViewResponseDto(requestList, acceptList, cancelList);
        }catch (Exception e){
            throw new NoExistScheduleException();
        }

        return responseDtos;
    }

}
