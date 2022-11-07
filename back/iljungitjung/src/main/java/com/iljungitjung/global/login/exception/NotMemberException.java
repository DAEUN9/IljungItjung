package com.iljungitjung.global.login.exception;

public class NotMemberException extends RuntimeException{
    public NotMemberException() {
        super("회원가입이 필요한 사용자 입니다.");
    }
}
