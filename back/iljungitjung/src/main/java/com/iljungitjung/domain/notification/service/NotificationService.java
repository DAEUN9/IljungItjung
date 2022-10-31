package com.iljungitjung.domain.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface NotificationService {
    NotificationResponseDto sendMessage(NotificationRequestDto requestDto) throws JsonProcessingException, URISyntaxException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException;
}
