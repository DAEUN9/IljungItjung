package com.iljungitjung.domain.notification.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter
public class NotificationResponseDto {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;

    @ConstructorProperties(value = {"requestId", "requestTime", "statusCode", "statusName"})
    public NotificationResponseDto(String requestId, LocalDateTime requestTime, String statusCode, String statusName) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

}
