package com.iljungitjung.domain.notification.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class NotificationRequestDto {
    private String content;
    @Valid
    @NotNull(message = "message list는 필수로 입력해야 합니다.")
    private List<NotificationMessageDto> messages;

    @Builder
    public NotificationRequestDto (List<NotificationMessageDto> messages, String content) {
        this.content = content;
        this.messages = messages;
    }
}
