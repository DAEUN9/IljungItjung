package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;

import javax.servlet.http.HttpSession;

public interface PhoneService {
    String requestRandomNumber(String phone, HttpSession httpSession);
    void confirmRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession httpSession);
}
