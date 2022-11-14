package com.iljungitjung.global.login.exception;

public class ExpireRedisUserException extends RuntimeException{
    public ExpireRedisUserException() {
        super("로그인 세션이 만료되었습니다.");
    }
}
