package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ScheduleRepository scheduleRepository;
    private final CategoryRepository categoryRepository;

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
        Schedule schedule = reservationRequestDto.toScheduleEntity(reservationRequestDto, startDate, endDate, category.getColor(), Type.REQUEST);
        scheduleRepository.save(schedule);
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto) {
        Schedule schedule = scheduleRepository.findScheduleById(id).orElseThrow(()->{
            throw new NoExistScheduleDetailException();
        });
        if(reservationManageRequestDto.isAccept()){
            schedule.accpeted();
        }else{
           scheduleRepository.delete(schedule);
        }
        return new ReservationIdResponseDto(schedule.getId());
    }

    @Override
    @Transactional
    public ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        Date endDate;
        try{
            startDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getStartTime());
            endDate = formatter.parse(reservationBlockRequestDto.getDate()+reservationBlockRequestDto.getEndTime());
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        Schedule schedule = reservationBlockRequestDto.toScheduleEntity(reservationBlockRequestDto, startDate, endDate);
        scheduleRepository.save(schedule);
        return new ReservationIdResponseDto(schedule.getId());

    }

}
