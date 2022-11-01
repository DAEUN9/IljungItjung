package com.iljungitjung.domain.notification.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

@Getter

public class NotificationResponseDto {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;

    //Jackson에서는 동일한 이름의 JSON속성값을 생성자로 넘겨줌
    @ConstructorProperties(value = {"requestId", "requestTime", "statusCode", "statusName"})
    public NotificationResponseDto(String requestId, LocalDateTime requestTime, String statusCode, String statusName) {
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

}
