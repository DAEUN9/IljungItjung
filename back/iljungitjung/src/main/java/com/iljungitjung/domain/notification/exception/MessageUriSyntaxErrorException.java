package com.iljungitjung.domain.notification.exception;

public class MessageUriSyntaxErrorException extends RuntimeException{
    public MessageUriSyntaxErrorException(){ super("메시지 전송 URI 형식이 맞지 않습니다.");}
}
