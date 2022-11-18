package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.dto.PhoneConfirmRequestDto;
import com.iljungitjung.domain.notification.entity.Phone;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.notification.exception.phone.ExpireRandomNumException;
import com.iljungitjung.domain.notification.exception.phone.IncorrectPhonenumException;
import com.iljungitjung.domain.notification.exception.phone.IncorrectRandomNumberException;
import com.iljungitjung.domain.notification.repository.PhoneRepository;
import com.iljungitjung.domain.notification.service.PhoneService;
import com.iljungitjung.domain.notification.service.PhoneServiceImpl;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.TemporaryUser;
import com.iljungitjung.global.login.exception.ExpireTemporaryUserException;
import com.iljungitjung.global.login.repository.TemporaryUserRepository;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;

import java.util.Optional;

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
    private PhoneRepository phoneRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TemporaryUserRepository temporaryUserRepository;
    @MockBean
    private HttpSession httpSession;
    @BeforeEach
    public void init(){
        phoneService = new PhoneServiceImpl(userRepository, temporaryUserRepository, phoneRepository, notificationCorrespondence);
    }

    @Test
    @DisplayName("인증번호 전송")
    public void sendRandomNumberMessage() throws Exception {
        String id = "1";
        String phone = "01000000001";

        when(httpSession.getId()).thenReturn(id);
        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(statusAccepted()));

        String response = phoneService.requestRandomNumber(phone, httpSession);
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
        Assertions.assertEquals(response.length(), 6);
    }

    @Test
    @DisplayName("이미 인증번호가 전송됐으면 재전송 후 인증번호 반환")
    public void existPhoneSendRandomNumber() throws Exception {
        String id = "1";
        String phonenum = "01012341234";

        when(httpSession.getId()).thenReturn(id);
        when(notificationCorrespondence.makeHeaders()).thenReturn(new HttpHeaders());
        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class))).thenReturn(new NotificationResponseDto(statusAccepted()));
        when(phoneRepository.existsById(id)).thenReturn(true);

        String response = phoneService.requestRandomNumber(phonenum, httpSession);
        verify(notificationCorrespondence, times(1)).sendNcloud(any(HttpEntity.class));
        Assertions.assertEquals(response.length(), 6);
    }


    @Test
    @DisplayName("다른 유저가 사용중인 전화번호일때 중복 메시지 반환")
    public void duplicatePhoneNumberConflict() throws Exception {
        String phone = "01012341234";

        when(userRepository.existsUserByPhonenum(phone)).thenReturn(true);
        String response = phoneService.requestRandomNumber(phone, httpSession);

        Assertions.assertEquals(response, PRESENTED_NUMBER);
    }

    @Test
    @DisplayName("ncloud 서버에서 문자 전송 실패")
    public void errorCorrespondenceNcoloud() throws Exception {
        String phone = "01012341234";

        when(notificationCorrespondence.sendNcloud(any(HttpEntity.class)))
                .thenReturn(new NotificationResponseDto(HttpStatus.BAD_GATEWAY.toString()));

        assertThatThrownBy(() -> phoneService.requestRandomNumber(phone, httpSession))
                .isInstanceOf(FailSendMessageException.class);
    }

    @Test
    @DisplayName("알맞은 인증번호와 전화번호를 입력하면 인증 성공")
    public void successConfirmRandomNumber() throws Exception {
        String id = "1";
        String phonenum = "01012341234";
        String randomNum = "123";
        String email = "email";

        PhoneConfirmRequestDto requestDto = new PhoneConfirmRequestDto(phonenum, randomNum);
        Phone phone = Phone.builder()
                .randomNumber(randomNum)
                .id(id)
                .phonenum(phonenum)
                .build();
        TemporaryUser temporaryUser = TemporaryUser.builder()
                .email(email)
                .id(id)
                .phonenum(phonenum)
                .build();

        when(httpSession.getId()).thenReturn(id);
        when(phoneRepository.findById(id)).thenReturn(Optional.of(phone));
        when(temporaryUserRepository.findById(id)).thenReturn(Optional.of(temporaryUser));

        phoneService.confirmRandomNumber(requestDto, httpSession);
    }

    @Test
    @DisplayName("틀린 인증번호를 입력하면 IncorrectRandomNumberException 발생")
    public void confirmIncorrectRandomNumber() throws Exception {
        String id = "1";
        String phonenum = "01012341234";
        String randomNum = "123";
        String incorrectRandomNum = "111";
        String email = "email";

        PhoneConfirmRequestDto requestDto = new PhoneConfirmRequestDto(phonenum, incorrectRandomNum);
        Phone phone = Phone.builder()
                .randomNumber(randomNum)
                .id(id)
                .phonenum(phonenum)
                .build();
        TemporaryUser temporaryUser = TemporaryUser.builder()
                .email(email)
                .id(id)
                .phonenum(phonenum)
                .build();

        when(httpSession.getId()).thenReturn(id);
        when(phoneRepository.findById(id)).thenReturn(Optional.of(phone));
        when(temporaryUserRepository.findById(id)).thenReturn(Optional.of(temporaryUser));

        assertThatThrownBy(() -> phoneService.confirmRandomNumber(requestDto, httpSession))
                .isInstanceOf(IncorrectRandomNumberException.class);
    }

    @Test
    @DisplayName("일치하지 않는 전화번호를 입력하면 IncorrectPhonenumException 발생")
    public void confirmIncorrectPhonnum() throws Exception {
        String id = "1";
        String phonenum = "01012341234";
        String incorrectPhonenum = "01011111111";
        String randomNum = "123";
        String email = "email";

        PhoneConfirmRequestDto requestDto = new PhoneConfirmRequestDto(incorrectPhonenum, randomNum);
        Phone phone = Phone.builder()
                .randomNumber(randomNum)
                .id(id)
                .phonenum(phonenum)
                .build();
        TemporaryUser temporaryUser = TemporaryUser.builder()
                .email(email)
                .id(id)
                .phonenum(phonenum)
                .build();

        when(httpSession.getId()).thenReturn(id);
        when(phoneRepository.findById(id)).thenReturn(Optional.of(phone));
        when(temporaryUserRepository.findById(id)).thenReturn(Optional.of(temporaryUser));

        assertThatThrownBy(() -> phoneService.confirmRandomNumber(requestDto, httpSession))
                .isInstanceOf(IncorrectPhonenumException.class);
    }

    @Test
    @DisplayName("인증번호가 만료됐으면 ExpireRandomNumException 발생")
    public void expirationRandomNum() throws Exception {
        String id = "1";
        String phonenum = "01012341234";
        String randomNum = "123";
        String email = "email";
        PhoneConfirmRequestDto requestDto = new PhoneConfirmRequestDto(phonenum, randomNum);
        TemporaryUser temporaryUser = TemporaryUser.builder()
                .email(email)
                .id(id)
                .phonenum(phonenum)
                .build();

        when(httpSession.getId()).thenReturn(id);
        when(temporaryUserRepository.findById(id)).thenReturn(Optional.of(temporaryUser));

        assertThatThrownBy(() -> phoneService.confirmRandomNumber(requestDto, httpSession))
                .isInstanceOf(ExpireRandomNumException.class);
    }

    @Test
    @DisplayName("임시 유저가 만료됐으면 ExpireRandomNumException 발생")
    public void expirationTemporaryUser() throws Exception {
        String id = "1";
        String phonenum = "01012341234";
        String randomNum = "123";
        String email = "email";
        PhoneConfirmRequestDto requestDto = new PhoneConfirmRequestDto(phonenum, randomNum);
        Phone phone = Phone.builder()
                .randomNumber(randomNum)
                .id(id)
                .phonenum(phonenum)
                .build();

        when(httpSession.getId()).thenReturn(id);
        when(phoneRepository.findById(id)).thenReturn(Optional.of(phone));

        assertThatThrownBy(() -> phoneService.confirmRandomNumber(requestDto, httpSession))
                .isInstanceOf(ExpireTemporaryUserException.class);
    }

    private String statusAccepted() {
        return Integer.toString(HttpStatus.ACCEPTED.value());
    }
}
