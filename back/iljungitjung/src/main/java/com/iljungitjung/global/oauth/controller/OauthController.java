package com.iljungitjung.global.oauth.controller;

import com.iljungitjung.global.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/kakao")
    public void kakaoOauthService(@RequestParam("code") String code, @RequestParam("client-uri") String clientUri, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        kakaoLoginService.sendRedirectToClientUriWithEmail(clientUri, code, request, response, session);
    }
}
