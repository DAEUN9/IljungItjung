package com.iljungitjung.domain.notification.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationMessageDto {
    private String to;
    private String content;

    @Builder
    public NotificationMessageDto(String to, String content) {
        this.to = to;
        this.content = content;
    }
}


