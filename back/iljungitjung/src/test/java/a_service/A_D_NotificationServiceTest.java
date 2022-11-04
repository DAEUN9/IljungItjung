package a_service;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


@DisplayName("알림 서비스")
public class A_D_NotificationServiceTest extends AbstractServiceTest{

    @Test
    @DisplayName("알림 전송")
    public void A() throws Exception {
        //given
        List<NotificationMessageDto> messageList = new ArrayList<>();
        NotificationMessageDto messageDto = new NotificationMessageDto("01071527518", "반가워요");
        messageList.add(messageDto);
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(messageList);
        //when
        NotificationResponseDto responseDto = notificationService.sendMessage(notificationRequestDto);
        //then
        Assertions.assertEquals(responseDto.getStatusCode(), "202");
    }
}
