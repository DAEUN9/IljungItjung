package com.iljungitjung.domain.notification;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("알림 서비스")
@ExtendWith(SpringExtension.class)
public class NotificationServiceTest {

    private NotificationService notificationService;
    @MockBean
    private NotificationCorrespondence notificationCorrespondence;
    @BeforeEach
    public void init(){
        notificationService = new NotificationServiceImpl(notificationCorrespondence);
    }

    @Test
    @DisplayName("메시지 전송")
    public void A() throws Exception {
        String message = "하이";
        String phone = "01000000000";

        NotificationMessage notificationMessage = new NotificationMessage(phone, message);
        List<NotificationMessage> messageList = new ArrayList<>();
        messageList.add(notificationMessage);
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.createFromMessages(messageList);


        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        NotificationResponseDto responseDto = notificationService.sendMessage(notificationRequestDto);
        Assertions.assertEquals(responseDto.getStatusCode(), "202");
    }

    @Test
    @DisplayName("예약 신청 알림 문자 전송")
    public void B() throws Exception {

        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = "01000000000";
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

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        notificationService.autoReservationMessage(schedule);
        verify(notificationCorrespondence, times(1)).makeHeaders();
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
    }

    @Test
    @DisplayName("예약 승인 문자 전송")
    public void C() throws Exception {
        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = "01000000000";
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
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        notificationService.autoReservationMessage(schedule);
        verify(notificationCorrespondence, times(1)).makeHeaders();
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
    }

    @Test
    @DisplayName("예약 신청 거절 문자 전송")
    public void D() throws Exception {
        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = "01000000000";
        String email = "email";
        String cancelFrom = "제공자";

        User userFrom = User.builder()
                .nickname(userFromNickname)
                .email(email)
                .build();
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

        schedule.canceled(cancelFrom, "");

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        notificationService.autoReservationMessage(schedule);
        verify(notificationCorrespondence, times(1)).makeHeaders();
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));

    }

    @Test
    @DisplayName("예약 신청 취소 문자 전송")
    public void E() throws Exception {
        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = "01000000000";
        String email = "email";
        String cancelFrom = "사용자";

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

        schedule.canceled(cancelFrom, "");

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        notificationService.autoReservationMessage(schedule);
        verify(notificationCorrespondence, times(1)).makeHeaders();
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
    }

    @Test
    @DisplayName("예약 취소 문자 전송")
    public void F() throws Exception {
        String categoryName = "파마";

        String userFromNickname = "1";
        String userToNickname = "2";
        String phone = "01000000000";
        String email = "email";

        User userFrom = User.builder()
                .nickname(userFromNickname)
                .email(email)
                .build();
        User userTo = User.builder()
                .nickname(userToNickname)
                .phonenum(phone)
                .build();
        Schedule schedule = Schedule.builder()
                .type(Type.ACCEPT)
                .userFrom(userFrom)
                .userTo(userTo)
                .endDate(new Date())
                .startDate(new Date())
                .categoryName(categoryName)
                .phonenum(phone).build();
        schedule.deleted();

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto("202"));

        notificationService.autoReservationMessage(schedule);
    }

}
