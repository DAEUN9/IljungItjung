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
    private String content;
    @Valid
    @NotNull(message = "message list는 필수로 입력해야 합니다.")
    private List<NotificationMessageDto> messages;


    public NotificationRequestDto (List<NotificationMessageDto> messages) {
        this.content = "필수";
        this.messages = messages;
    }

}
