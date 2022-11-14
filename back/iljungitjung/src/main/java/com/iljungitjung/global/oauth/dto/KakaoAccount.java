package com.iljungitjung.global.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KakaoAccount {
    @JsonProperty("has_email")
    private Boolean hasEmail;

    @JsonProperty("email_needs_agreement")
    private Boolean emailNeedsAgreement;

    @JsonProperty("is_email_valid")
    private Boolean isEmailValid;

    @JsonProperty("is_email_verified")
    private Boolean isEmailVerified;

    @JsonProperty("email")
    private String email;

    @JsonProperty("profile")
    private KakaoUserProfile kakaoUserProfile;
}
