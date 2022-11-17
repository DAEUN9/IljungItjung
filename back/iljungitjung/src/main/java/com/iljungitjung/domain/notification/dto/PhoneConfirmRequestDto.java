package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneConfirmRequestDto {
    private String phonenum;
    private String randomNumber;

}
