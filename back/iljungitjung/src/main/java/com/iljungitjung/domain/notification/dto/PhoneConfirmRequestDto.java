package com.iljungitjung.domain.notification.dto;

import lombok.Getter;

@Getter
public class PhoneConfirmRequestDto {
    private String phonenum;
    private String randomNumber;

    public PhoneConfirmRequestDto(String phonenum, String randomNumber) {
        this.phonenum = phonenum;
        this.randomNumber = randomNumber;
    }
}
