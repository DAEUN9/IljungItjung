package com.iljungitjung.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class NotificationMessageRequestDto {
    @JsonProperty("type")
    private String type;
    @JsonProperty("from")
    private String from;
    // 추후 LMS 기준으로 변경
    @JsonProperty("content")
    private String content;
    @JsonProperty("messages")
    private List<NotificationMessageDto> messages;

    @Builder
    public NotificationMessageRequestDto(NotificationRequestDto requestDto, String phone) {
        this.type = "SMS";
        this.from = phone;
        this.content = "기본메시지";
        this.messages = requestDto.getMessages();
    }
}
