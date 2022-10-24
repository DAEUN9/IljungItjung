package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;

import java.text.ParseException;
import java.util.List;

public interface ScheduleService {
    ScheduleViewResponseDto scheduleView(String nickname);
    ScheduleViewDetailResponseDto scheduleViewDetail(Long id);
}
