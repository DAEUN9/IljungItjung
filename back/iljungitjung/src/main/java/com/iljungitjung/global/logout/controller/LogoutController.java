package com.iljungitjung.global.logout.controller;

import com.iljungitjung.global.login.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/logout")
@RequiredArgsConstructor
public class LogoutController {

    private final RedisUserRepository redisUserRepository;

    @DeleteMapping
    public void logoutUser(HttpSession session){
        String sessionId = session.getId();
        redisUserRepository.deleteById(sessionId);
        session.invalidate();
    }
}
