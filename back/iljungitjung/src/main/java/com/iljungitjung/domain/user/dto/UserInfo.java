package com.iljungitjung.domain.user.dto;

import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.global.login.entity.RedisUser;
import lombok.Getter;

@Getter
public class UserInfo {
    private String nickname;

    private String email;

    private String imagePath;

    public UserInfo(RedisUser redisUser){
        this.nickname = redisUser.getNickname();
        this.email = redisUser.getEmail();
        this.imagePath = redisUser.getProfileImg();
    }

    public UserInfo(User user){
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.imagePath = user.getImagePath();
    }
}
