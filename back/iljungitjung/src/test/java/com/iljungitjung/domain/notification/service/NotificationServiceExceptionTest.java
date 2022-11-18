package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.notification.service.NotificationServiceImpl;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("알림 서비스 예외")
@ExtendWith(SpringExtension.class)
public class NotificationServiceExceptionTest {

    private NotificationService notificationService;

    @MockBean
    private NotificationCorrespondence notificationCorrespondence;

    @BeforeEach
    public void init(){
        notificationService = new NotificationServiceImpl(notificationCorrespondence);
    }

    @Test
    @DisplayName("ncloud 메세지 전송 예외 발생")
    public void errorCorrespondenceNcloud() throws Exception {
        String content = "하이";
        String phone = "01000000000";

        NotificationMessage message = new NotificationMessage(phone, content);
        List<NotificationMessage> messageList = makeMessageList(message);
        NotificationRequestDto requestDto = NotificationRequestDto.createFromMessages(messageList);

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(HttpStatus.BAD_GATEWAY.toString()));

        assertThatThrownBy(() -> notificationService.sendMessage(requestDto))
                .isInstanceOf(FailSendMessageException.class);
    }

    @Test
    @DisplayName("전화번호가 존재하지 않는 예약은 문자를 보내지 않음")
    public void notSendMessageNullPhonenum() throws Exception {
        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = null;
        String email = "email";

        User userFrom = User.builder()
                .nickname(userFromNickname)
                .email(email).build();
        User userTo = User.builder()
                .nickname(userToNickname)
                .phonenum(phone).build();
        Schedule schedule = Schedule.builder()
                .type(Type.REQUEST)
                .userFrom(userFrom)
                .userTo(userTo)
                .endDate(new Date())
                .startDate(new Date())
                .categoryName(categoryName)
                .phonenum(phone).build();
        schedule.accpeted();
        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(statusAccepted()));

        notificationService.autoReservationMessage(schedule);
        verify(notificationCorrespondence, times(0)).sendNcloud(any(HttpEntity.class));
    }

    private List<NotificationMessage> makeMessageList(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private String statusAccepted() {return Integer.toString(HttpStatus.ACCEPTED.value());}
}
