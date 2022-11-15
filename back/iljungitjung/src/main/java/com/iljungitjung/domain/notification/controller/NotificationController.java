package com.iljungitjung.domain.notification.controller;

import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RestController
@Validated
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<CommonResponse> createNotification(
            @RequestBody @Valid NotificationRequestDto requestDto
    ) {
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(notificationService.sendMessage(requestDto)), HttpStatus.OK);
    }

    @GetMapping("/phones/{phone}")
    public ResponseEntity<CommonResponse> authenticatePhone (
             @Pattern(regexp = "01\\d{8,9}", message = "전화번호는 01로 시작하고 10~11자리의 숫자만 입력가능합니다.")
             @PathVariable("phone")
             String phone
    ) {
        String response = notificationService.requestRandomNumber(phone);
        if(response.length() == 4) {
            return new ResponseEntity<>(CommonResponse.getSuccessResponse(response), HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonResponse.getErrorResponse(response), HttpStatus.CONFLICT);
    }

}
