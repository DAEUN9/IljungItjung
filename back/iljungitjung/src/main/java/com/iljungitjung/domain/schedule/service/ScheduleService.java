package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;

import javax.servlet.http.HttpSession;

public interface ScheduleService {
    ScheduleViewResponseDto scheduleView(String nickname, String startDate, String endDate, HttpSession httpSession);
    ScheduleViewDetailResponseDto scheduleViewDetail(Long id);
}
