package com.iljungitjung.domain.notification.controller;

import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;
import com.iljungitjung.domain.notification.service.PhoneService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RestController
@Validated
public class PhoneController {
    private final PhoneService phoneService;
    private final String EXPIRATION = "인증번호가 틀리거나 만료되었습니다.";

    @GetMapping("/phones/{phone}")
    public ResponseEntity<CommonResponse> sendAuthenticatePhone (
            @Pattern(regexp = "01\\d{8,9}", message = "전화번호는 01로 시작하고 10~11자리의 숫자만 입력가능합니다.")
            @PathVariable("phone")
            String phone,
            HttpSession httpSession
    ) {
        String response = phoneService.requestRandomNumber(phone, httpSession);
        if(response.length() == 6) {
            return new ResponseEntity<>(CommonResponse.getSuccessResponse(response), HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonResponse.getErrorResponse(response), HttpStatus.CONFLICT);
    }

    @PutMapping("/phones")
    public ResponseEntity<CommonResponse> confirmAuthenticatePhone (
            PhoneConfirmRequestDto requestDto,
            HttpSession httpSession
    ) {
        Boolean confirm = phoneService.comfirmRandomNumber(requestDto, httpSession);
        if(confirm) {
            return new ResponseEntity<>(CommonResponse.getSuccessResponse(confirm), HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonResponse.getErrorResponse(EXPIRATION), HttpStatus.CONFLICT);
    }
}
