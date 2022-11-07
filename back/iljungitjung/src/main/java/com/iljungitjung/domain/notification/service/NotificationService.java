package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;


public interface NotificationService {
    NotificationResponseDto sendMessage(NotificationRequestDto requestDto);
    void buildTemplate(Schedule schedule);
}
