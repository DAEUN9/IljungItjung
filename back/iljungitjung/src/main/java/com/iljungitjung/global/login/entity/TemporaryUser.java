package com.iljungitjung.global.login.entity;

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
    @Builder
    public TemporaryUser(String id, String email, String profileImg){
        this.id = id;
        this.email = email;
        this.profileImg = profileImg;
    }
}
