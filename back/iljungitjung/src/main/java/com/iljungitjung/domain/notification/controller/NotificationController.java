package com.iljungitjung.domain.notification.controller;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<CommonResponse> createNotification(
            @RequestBody @Valid NotificationRequestDto requestDto
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(notificationService.sendMessage(requestDto)), HttpStatus.OK);
    }
}
