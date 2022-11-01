package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class NotificationMessageRequestDto {
    // SMS, LMS, MMS (소문자 가능)
    private String type;
    // 발신번호
    private String from;
    // 기본 메시지 내용(공통)
    private String content;
    private List<NotificationMessageDto> messages;

    public NotificationMessageRequestDto(NotificationRequestDto requestDto) {
        this.type = "SMS";
        this.from = "01071527518";
        this.content = requestDto.getContent();
        this.messages = requestDto.getMessages();
    }
}
