package com.iljungitjung.domain.notification.exception;

public class ConvertToJsonErrorException extends RuntimeException{
    public ConvertToJsonErrorException(){ super("객체를 JSON으로 변경이 불가합니다.");}
}
