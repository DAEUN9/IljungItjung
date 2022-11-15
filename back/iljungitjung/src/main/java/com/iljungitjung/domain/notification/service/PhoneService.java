package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;

public interface PhoneService {
    String requestRandomNumber(String phone);

    void sendMessage(NotificationRequestDto notificationRequestDto);
    String makeRandomNumber();
}
