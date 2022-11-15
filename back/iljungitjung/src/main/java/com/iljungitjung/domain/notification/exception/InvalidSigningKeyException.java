package com.iljungitjung.domain.notification.exception;

public class InvalidSigningKeyException extends RuntimeException{

    public InvalidSigningKeyException(){ super("유효하지 않은 Singning Key 입니다.");}
}
