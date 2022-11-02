package com.iljungitjung.global.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class KakaoUserInfoResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("connected_at")
    private Date connectedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
}
