package com.iljungitjung.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    @Pattern(regexp = "^01\\d{8,9}$", message = "수신인 전화번호(to)는 01로 시작하고 10~11자리의 숫자만 입력가능합니다.")
    @NotBlank(message = "수신인 전화번호(to)는 비워둘 수 없습니다.")
    private String to;

    @NotBlank(message = "메시지(content)는 비워둘 수 없습니다.")
    @Size(min=1, max=80, message = "메시지(content) 길이는 1~80자 입니다.")
    private String content;


}


