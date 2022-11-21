package com.iljungitjung.global.login.interceptor;

import com.iljungitjung.global.login.exception.ExpireRedisUserException;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private final RedisUserRepository redisUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("request path : {}", request.getRequestURL());
        if(request.getMethod().equals(HttpMethod.OPTIONS)) return true;

        if(isSignUpRequest(request)) {
            log.debug("signup");
            return true;
        }

        if(isUpdateUserRequest(request)){
            log.debug("update user");
            return true;
        }

        if(isAuthenticatePhonenum(request)){
            log.debug("authenticate phonenum");
            return true;
        }

        String sessionId = request.getSession().getId();
        log.debug("session Id : {}", sessionId);
        if(!redisUserRepository.existsById(sessionId)){
            throw new ExpireRedisUserException();
        }
        log.debug("session Id : {}", sessionId);
        return true;
    }

    private boolean isSignUpRequest(HttpServletRequest request) {
        log.debug(request.getMethod());
        log.debug(request.getRequestURI());
        if(request.getMethod().equals("POST") && request.getRequestURI().equals("/api/users")) return true;
        if(request.getMethod().equals("POST") && request.getRequestURI().equals("/users")) return true;
        return false;
    }

    private boolean isUpdateUserRequest(HttpServletRequest request){
        if(request.getMethod().equals("PUT") && request.getRequestURI().equals("/api/users")) return true;
        if(request.getMethod().equals("PUT") && request.getRequestURI().equals("/users")) return true;
        return false;
    }

    private boolean isAuthenticatePhonenum(HttpServletRequest request){
        if(request.getMethod().equals("PUT") && request.getRequestURI().equals("/api/phones")) return true;
        if(request.getMethod().equals("PUT") && request.getRequestURI().equals("/phones")) return true;
        if(request.getMethod().equals("POST") && request.getRequestURI().startsWith("/api/phones/")) return true;
        if(request.getMethod().equals("POST") && request.getRequestURI().startsWith("/phones/")) return true;
        return false;
    }
}
