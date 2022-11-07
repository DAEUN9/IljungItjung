package com.iljungitjung.domain.notification;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("알림 서비스")
@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;

    @Test
    @DisplayName("메시지 전송")
    public void A() throws Exception {
        String message = "하이";
        String phone = "01071527518";

        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(notificationMessageDto);
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(messageList);

        NotificationResponseDto responseDto = notificationService.sendMessage(notificationRequestDto);

        Assertions.assertEquals(responseDto.getStatusCode(), "202");
    }

}
