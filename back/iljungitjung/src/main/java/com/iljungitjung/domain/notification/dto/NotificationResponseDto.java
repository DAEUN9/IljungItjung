package com.iljungitjung.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class NotificationResponseDto {
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("requestTime")
    private LocalDateTime requestTime;
    @JsonProperty("statusCode")
    private String statusCode;
    @JsonProperty("statusName")
    private String statusName;

}
