package com.iljungitjung.domain.notification.service;


import com.iljungitjung.domain.notification.dto.*;
import com.iljungitjung.domain.notification.entity.Phone;
import com.iljungitjung.domain.notification.exception.FailSendMessageException;
import com.iljungitjung.domain.notification.repository.PhoneRepository;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;
    private final String AUTH_PHONE = "인증 번호를 입력해 주세요\n[%s]";
    private final String PRESENTED_NUMBER = "이미 존재하는 전화번호 입니다.";
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final RedisUserRepository redisUserRepository;
    private final NotificationCorrespondence notificationCorrespondence;

    @Override
    public String requestRandomNumber(String phone, HttpSession httpSession) {
        if (checkDuplicatePhone(phone)) {
            return PRESENTED_NUMBER;
        }
        String randomNum = makeRandomNumber();
        NotificationMessage message = new NotificationMessage(phone, makeAuthenticateContent(randomNum));
        sendMessage(NotificationRequestDto.createFromMessages(makeMessages(message)));
        savePhoneRandomNumber(new PhoneConfirmRequestDto(phone, randomNum), httpSession);
        return randomNum;
    }
    private List<NotificationMessage> makeMessages(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private void sendMessage(NotificationRequestDto requestDto) {
        HttpEntity<NotificationMessageRequestDto> body = makeBody(requestDto);
        checkStatusEqualsAccpeted(notificationCorrespondence.sendNcloud(body));
    }

    public NotificationResponseDto checkStatusEqualsAccpeted(NotificationResponseDto response){
        if(response.getStatusCode().equals(statusAccepted())) return response;
        throw new FailSendMessageException();
    }

    private String statusAccepted() {
        return HttpStatus.ACCEPTED.value()+"";
    }
    private HttpEntity<NotificationMessageRequestDto> makeBody(NotificationRequestDto requestDto) {
        HttpHeaders headers = notificationCorrespondence.makeHeaders();
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        return new HttpEntity<>(jsonBody, headers);
    }

    private String makeRandomNumber() {
        UUID uuid = UUID.randomUUID();
        return parseToShortUUID(uuid.toString());
    }

    private String parseToShortUUID(String uuid) {
        int uuidInt = ByteBuffer.wrap(uuid.getBytes()).getInt();
        return Integer.toString(uuidInt, 10);
    }

    private String makeAuthenticateContent(String number) {
        return String.format(AUTH_PHONE, number);
    }

    private boolean checkDuplicatePhone(String phone) {
        return userRepository.existsUserByPhonenum(phone);
    }

    //
    private void savePhoneRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession session) {
        String id = session.getId();
        if (phoneRepository.existsById(id)) {
            phoneRepository.deleteById(id);
        }
        Phone phone = Phone.builder()
                .phonenum(requestDto.getPhonenum())
                .id(id)
                .randomNumber(requestDto.getRandomNumber()).build();
        phoneRepository.save(phone);
    }

    @Override
    public boolean comfirmRandomNumber(PhoneConfirmRequestDto requestDto, HttpSession session) {
        Phone phone = phoneRepository.findById(session.getId()).orElse(null);
        if (phone ==null) {
            return false;
        }
        if (checkCorrectPhonnum(requestDto.getPhonenum(), phone)) {
            return true;
        }
        return false;
    }

    private boolean checkCorrectPhonnum(String phoenum, Phone phone) {
        if (phoenum.equals(phone.getPhonenum())) {
            return true;
        }
        return false;
    }
}
