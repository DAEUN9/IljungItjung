package com.iljungitjung.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpUserResponseDto {

    private final Long id;

    public SignUpUserResponseDto(Long id) {
        this.id=id;
    }
}
