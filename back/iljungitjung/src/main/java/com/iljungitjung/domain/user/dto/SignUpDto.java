package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.user.entity.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignUpDto {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
    private String nickname;

    @Size(min = 0, max = 50, message = "한줄소개는 최소 0자, 최대 50자만 가능합니다.")
    private String description;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)?([0-9]{7,8})$", message = "전화번호는 10~11자리의 숫자만 입력가능합니다.")
    private String phonenum;

    public User toEntity(){
        return User.builder()
                .nickname(this.nickname)
                .description(this.description)
                .phonenum(this.phonenum)
                .build();
    }
}
