package com.iljungitjung.global.login.entity;

import com.iljungitjung.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "user", timeToLive = 6000)
public class RedisUser {
    @Id
    private String id;

    private String nickname;

    private String email;

    public void setDataFromUser(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }

    @Builder
    public RedisUser(String id, String nickname, String email){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
