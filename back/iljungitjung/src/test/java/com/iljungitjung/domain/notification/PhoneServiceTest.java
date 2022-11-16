package com.iljungitjung.domain.notification;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.FailSendMessageException;
import com.iljungitjung.domain.notification.service.PhoneService;
import com.iljungitjung.domain.notification.service.PhoneServiceImpl;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("폰 서비스")
@ExtendWith(SpringExtension.class)
public class PhoneServiceTest {
    private final String PRESENTED_NUMBER = "이미 존재하는 전화번호 입니다.";
    private PhoneService phoneService;
    @MockBean
    private NotificationCorrespondence notificationCorrespondence;
    @MockBean
    private UserRepository userRepository;
    @BeforeEach
    public void init(){
        phoneService = new PhoneServiceImpl(userRepository, notificationCorrespondence);
    }

    @Test
    @DisplayName("인증번호 전송")
    public void sendMessageTest() throws Exception {
        String phone = "01000000001";

        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(statusAccepted()));

        phoneService.requestRandomNumber(phone);
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
    }

    @Test
    @DisplayName("4자리 수의 난수 생성 검증")
    public void makeRandomNumbersTest() throws Exception {
        String randomNum = phoneService.makeRandomNumber();
        Boolean isNumber = randomNum.chars().allMatch(Character::isDigit);

        Assertions.assertEquals(randomNum.length(), 4);
        Assertions.assertEquals(isNumber, true);
    }

    @Test
    @DisplayName("다른 유저가 사용중인 전화번호일때 중복 메시지 반환")
    public void duplicatePhoneNumberConflictTest() throws Exception {
        String phone = "01012341234";

        when(userRepository.existsUserByPhonenum(phone)).thenReturn(true);
        String response = phoneService.requestRandomNumber(phone);

        Assertions.assertEquals(response, PRESENTED_NUMBER);
    }

    @Test
    @DisplayName("ncloud 서버에서 문자 전송 실패")
    public void errorCorrespondenceNcoloud() throws Exception {
        String phone = "01012341234";

        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class)))
                .thenReturn(new NotificationResponseDto(HttpStatus.BAD_GATEWAY.toString()));

        assertThatThrownBy(() -> phoneService.requestRandomNumber(phone))
                .isInstanceOf(FailSendMessageException.class);
    }

    private String statusAccepted() {
        return HttpStatus.ACCEPTED.value()+"";
    }
}
