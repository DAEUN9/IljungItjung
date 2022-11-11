package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;


public interface NotificationService {
    NotificationResponseDto sendMessage(NotificationRequestDto requestDto);
    void autoReservationMessage(Schedule schedule);
}
