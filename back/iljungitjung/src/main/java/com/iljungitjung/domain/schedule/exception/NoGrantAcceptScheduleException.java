package com.iljungitjung.domain.schedule.exception;

public class NoGrantAcceptScheduleException extends RuntimeException{
    public NoGrantAcceptScheduleException() { super("해당 일정 수락 권한이 없습니다."); }
}
