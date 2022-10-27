package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewRequestDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;

public interface ScheduleService {
    ScheduleViewResponseDto scheduleView(String nickname, ScheduleViewRequestDto scheduleViewRequestDto);
    ScheduleViewDetailResponseDto scheduleViewDetail(Long id);
}
