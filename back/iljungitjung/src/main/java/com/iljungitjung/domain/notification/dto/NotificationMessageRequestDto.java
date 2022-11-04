package com.iljungitjung.domain.notification.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NotificationMessageRequestDto {
    private String type;
    private String from;
    private String content;
    private List<NotificationMessageDto> messages;

    @Builder
    public NotificationMessageRequestDto(NotificationRequestDto requestDto, String phone) {
        // 추후 LMS으로 변경
        this.type = "SMS";
        this.from = phone;
        this.content = "기본메시지";
        this.messages = requestDto.getMessages();
    }
}
