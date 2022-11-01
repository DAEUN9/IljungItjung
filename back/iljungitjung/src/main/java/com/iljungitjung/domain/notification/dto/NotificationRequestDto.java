package com.iljungitjung.domain.notification.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class NotificationRequestDto {
    private String recipientPhoneNumber;
    private String content;
    private List<NotificationMessageDto> messages;
}
