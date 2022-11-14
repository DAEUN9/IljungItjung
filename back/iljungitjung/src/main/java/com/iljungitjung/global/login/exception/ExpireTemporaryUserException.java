package com.iljungitjung.global.login.exception;

public class ExpireTemporaryUserException extends RuntimeException{
    public ExpireTemporaryUserException() {
        super("회원가입을 다시 해주세요");
    }
}
