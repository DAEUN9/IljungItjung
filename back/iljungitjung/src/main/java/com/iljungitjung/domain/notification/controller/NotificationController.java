package com.iljungitjung.domain.notification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/sms")
    public ResponseEntity<CommonResponse> createNotification(
            @RequestBody NotificationRequestDto requestDto
    ) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        NotificationResponseDto responseDto = notificationService.sendMessage(requestDto);
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(responseDto), HttpStatus.OK);
    }
}
