package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneConfirmRequestDto {
    @NotBlank
    private String phonenum;
    @NotBlank
    private String randomNumber;

}
