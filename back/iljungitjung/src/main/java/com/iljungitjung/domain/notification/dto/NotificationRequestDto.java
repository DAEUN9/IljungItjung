package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
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
    private List<NotificationMessage> messages;

    private NotificationRequestDto (List<NotificationMessage> messages){
        this.content = DEFAULT_CONTENT;
        this.messages = messages;
    }

    public static NotificationRequestDto createFromMessages(List<NotificationMessage> messages){
        return new NotificationRequestDto(messages);
    }
}
