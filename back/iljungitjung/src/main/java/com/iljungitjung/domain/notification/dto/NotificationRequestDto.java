package com.iljungitjung.domain.notification.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class NotificationRequestDto {
    private String content;
    private List<NotificationMessageDto> messages;

    @Builder
    public NotificationRequestDto (List<NotificationMessageDto> messages, String content) {
        this.content = content;
        this.messages = messages;
    }
}
