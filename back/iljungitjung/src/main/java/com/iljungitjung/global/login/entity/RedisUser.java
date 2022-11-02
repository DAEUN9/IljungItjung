package com.iljungitjung.global.login.entity;

import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.global.oauth.dto.KakaoUserInfoResponseDto;
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

    private String profileImg;

    public void setDataFromUserAndKakaoUserInfo(User user, KakaoUserInfoResponseDto kakaoUserInfo){
        this.nickname = user.getNickname();
        this.email = kakaoUserInfo.getKakaoAccount().getEmail();
        this.profileImg = kakaoUserInfo.getKakaoAccount().getKakaoUserProfile().getProfile_image_url();
    }

    @Builder
    public RedisUser(String id, String nickname, String email, String profileImg){
        this.id = id;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.email = email;
    }
}
