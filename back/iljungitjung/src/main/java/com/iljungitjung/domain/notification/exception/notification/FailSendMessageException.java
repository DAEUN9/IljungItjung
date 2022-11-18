package com.iljungitjung.domain.notification.exception.notification;

public class FailSendMessageException extends RuntimeException {
    public FailSendMessageException(){ super("메시지 전송에 실패했습니다.");}
}
