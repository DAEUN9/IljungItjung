package com.iljungitjung.domain.user.exception;

public class NoExistUserException extends RuntimeException{
    public NoExistUserException(){
        super("유저가 존재하지 않습니다.");
    }
}
