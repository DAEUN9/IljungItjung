package com.iljungitjung.global.login.exception;

public class NotMatchPhonenumException extends RuntimeException {
    public NotMatchPhonenumException() {
        super("인증되지 않은 전화번호 입니다.");
    }
}
