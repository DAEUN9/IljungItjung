package com.iljungitjung.global.login.controller;

import com.iljungitjung.global.login.service.KakaoLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final KakaoLoginService kakaoLoginService;

    @GetMapping("/kakao")
    public void redirectKakaoLoginPage(HttpServletRequest request, HttpServletResponse response){
        kakaoLoginService.sendRedirectToLoginPage(request, response);
    }
}
