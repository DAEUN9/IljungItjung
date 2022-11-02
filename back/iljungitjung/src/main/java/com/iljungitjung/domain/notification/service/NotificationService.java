package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;


public interface NotificationService {
    NotificationResponseDto sendMessage(NotificationRequestDto requestDto);
}
