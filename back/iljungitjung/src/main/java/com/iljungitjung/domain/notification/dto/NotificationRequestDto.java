package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificationRequestDto {
    private String recipientPhoneNumber;
    private String title;
    private String content;
}
