package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;

import javax.servlet.http.HttpSession;

public interface PhoneService {
    String requestRandomNumber(String phone, HttpSession httpSession);
    boolean comfirmRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession httpSession);
}
