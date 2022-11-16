package com.iljungitjung.domain.notification.exception;

public class FailSignatureKeyErrorException extends RuntimeException{
    public FailSignatureKeyErrorException(){ super("서명키 생성에 실패했습니다.");}
}
