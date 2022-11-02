package com.iljungitjung.global.login.exception;

public class ExpireLoginUserException extends RuntimeException{
    public ExpireLoginUserException() {
        super("로그인을 다시 해주세요");
    }
}
