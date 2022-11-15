package com.iljungitjung.global.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LoginService {
    void sendRedirectToLoginPage(HttpServletRequest request, HttpServletResponse response);

    void sendRedirectToClientUriWithEmail(String clientUri, String code, HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
