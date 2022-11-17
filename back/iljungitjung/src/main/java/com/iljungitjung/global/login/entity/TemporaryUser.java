package com.iljungitjung.global.login.entity;

import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.global.login.exception.NotMatchPhonenumException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "temporaryuser", timeToLive = 600)
public class TemporaryUser {
    @Id
    private String id;
    private String email;

    private String profileImg;
    private String phonenum;
    @Builder
    public TemporaryUser(String id, String email, String profileImg){
        this.id = id;
        this.email = email;
        this.profileImg = profileImg;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public void matchPhonenum(SignUpDto signUpDto) {
        if (!signUpDto.getPhonenum().equals(phonenum)) {
            throw new NotMatchPhonenumException();
        }
    }
}
