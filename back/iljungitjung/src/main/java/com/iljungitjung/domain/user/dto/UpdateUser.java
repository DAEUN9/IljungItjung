package com.iljungitjung.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class UpdateUser {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
    private String nickname;
    @Size(max = 50, message = "한줄 소개는 0 ~ 50자만 가능합니다.")
    private String introduction;
    @Size(max = 300, message = "설명은 0 ~ 300자만 가능 합니다.")
    private String description;
}
