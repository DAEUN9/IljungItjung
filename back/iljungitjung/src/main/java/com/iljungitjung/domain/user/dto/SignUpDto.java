package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
    private String nickname;

    @Size(min = 0, max = 50, message = "한줄소개는 최소 0자, 최대 50자만 가능합니다.")
    private String introduction;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01\\d{8,9}$", message = "전화번호는 01로 시작하고 10~11자리의 숫자만 입력가능합니다.")
    private String phonenum;

    public User toEntity(){
        return User.builder()
                .nickname(this.nickname)
                .introduction(this.introduction)
                .phonenum(this.phonenum)
                .build();
    }
}

