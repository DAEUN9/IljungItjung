package com.iljungitjung.domain.notification;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.FailSendMessageException;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.notification.service.NotificationServiceImpl;
import com.iljungitjung.global.scheduler.NotificationNcloud;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("알림 서비스 예외")
@ExtendWith(SpringExtension.class)
public class NotificationServiceExceptionTest {

    private NotificationService notificationService;

    @MockBean
    private NotificationNcloud notificationNcloud;

    @BeforeEach
    public void init(){
        notificationService = new NotificationServiceImpl(notificationNcloud);
    }

    @Test
    @DisplayName("ncloud 메세지 전송 예외 발생")
    public void A() throws Exception {
        String message = "하이";
        String phone = "01000000000";

        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(notificationMessageDto);
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(messageList);

        when(notificationNcloud.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationNcloud.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(HttpStatus.BAD_GATEWAY.toString()));

        assertThatThrownBy(() -> notificationService.sendMessage(notificationRequestDto))
                .isInstanceOf(FailSendMessageException.class);
    }
}
