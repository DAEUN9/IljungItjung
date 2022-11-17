package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneConfirmRequestDto {
    private String phonenum;
    private String randomNumber;

}
