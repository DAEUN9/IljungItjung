package com.iljungitjung.domain.notification.exception;

public class SecretKeyEncodingException extends RuntimeException{
    public SecretKeyEncodingException(){ super("Secret Key를 인코딩 할 수 없습니다.");}
}
