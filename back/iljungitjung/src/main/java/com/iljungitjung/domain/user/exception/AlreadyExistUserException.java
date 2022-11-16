package com.iljungitjung.domain.user.exception;

public class AlreadyExistUserException extends RuntimeException{
    public AlreadyExistUserException() {
        super("이미 존재하는 사용자 입니다.");
    }
}
