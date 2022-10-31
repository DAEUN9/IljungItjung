package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.user.entity.User;
import lombok.Getter;

@Getter
public class SignUpDto {
    private String nickname;
    private String description;
    private String phonenum;

    public User toEntity(){
        return User.builder()
                .nickname(this.nickname)
                .description(this.description)
                .phonenum(this.phonenum)
                .build();
    }
}
