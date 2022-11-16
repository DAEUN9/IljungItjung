package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;

public interface PhoneService {
    String requestRandomNumber(String phone);
    String makeRandomNumber();
}
