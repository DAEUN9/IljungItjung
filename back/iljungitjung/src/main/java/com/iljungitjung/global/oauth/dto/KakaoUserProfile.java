package com.iljungitjung.global.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class KakaoUserProfile {
    @JsonProperty("profile_image_url")
    private String profile_image_url;
}
