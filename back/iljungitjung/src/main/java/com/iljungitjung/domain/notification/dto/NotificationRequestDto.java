package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDto {
    private final String DEFAULT_CONTENT = "필수";
    private String content;
    @Valid
    @NotNull(message = "message list는 필수로 입력해야 합니다.")
    private List<NotificationMessageDto> messages;

    private NotificationRequestDto (List<NotificationMessageDto> messages){
        this.content = DEFAULT_CONTENT;
        this.messages = messages;
    }

    public static NotificationRequestDto createFromMessages(List<NotificationMessageDto> messages){
        return new NotificationRequestDto(messages);
    }
}
