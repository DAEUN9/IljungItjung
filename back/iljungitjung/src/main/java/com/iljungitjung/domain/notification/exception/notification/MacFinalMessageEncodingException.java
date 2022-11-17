package com.iljungitjung.domain.notification.exception.notification;

public class MacFinalMessageEncodingException extends RuntimeException{
    public MacFinalMessageEncodingException(){ super("MAC 종료 메시지를 인코딩 할 수 없습니다.");}
}
