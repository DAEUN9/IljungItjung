package com.iljungitjung.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
public class MessageRequestDto {
    @JsonProperty("type")
    private String type;
    @JsonProperty("from")
    private String from;
    @JsonProperty("content")
    private String content;
    @JsonProperty("messages")
    private List<NotificationMessage> messages;

    @Builder
    public MessageRequestDto(NotificationRequestDto requestDto, String phone) {
        this.type = choiceType(requestDto);
        this.from = phone;
        this.content = "기본메시지";
        this.messages = requestDto.getMessages();
    }

    private String choiceType(NotificationRequestDto requestDto) {
        if (requestDto.getContent().length() >= 50) {
            return "LMS";
        }
        return "SMS";
    }
}
