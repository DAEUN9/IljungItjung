package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.ScheduleViewResponseDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleViewResponseDto> scheduleView(String nickname);
}
