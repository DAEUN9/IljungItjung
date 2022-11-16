package com.iljungitjung.domain.notification.controller;

import com.iljungitjung.domain.notification.service.PhoneService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RestController
@Validated
public class PhoneController {
    private final PhoneService phoneService;

    @GetMapping("/phones/{phone}")
    public ResponseEntity<CommonResponse> authenticatePhone (
            @Pattern(regexp = "01\\d{8,9}", message = "전화번호는 01로 시작하고 10~11자리의 숫자만 입력가능합니다.")
            @PathVariable("phone")
            String phone
    ) {
        String response = phoneService.requestRandomNumber(phone);
        if(response.length() == 4) {
            return new ResponseEntity<>(CommonResponse.getSuccessResponse(response), HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonResponse.getErrorResponse(response), HttpStatus.CONFLICT);
    }
}
