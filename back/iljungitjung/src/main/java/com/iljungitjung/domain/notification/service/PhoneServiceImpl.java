package com.iljungitjung.domain.notification.service;


import com.iljungitjung.domain.notification.dto.*;
import com.iljungitjung.domain.notification.exception.FailSendMessageException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;
    private final String TEMP_PHONE = "01000000000";
    private final String AUTH_PHONE = "인증 번호를 입력해 주세요/\n[%s]";
    private final String PRESENTED_NUMBER = "이미 존재하는 전화번호 입니다.";
    private final UserRepository userRepository;
    private final NotificationCorrespondence notificationCorrespondence;

    @Override
    public String requestRandomNumber(String phone) {
        if (checkDuplicatePhone(phone)) {
            return PRESENTED_NUMBER;
        }
        String randomNum = makeRandomNumber();
        NotificationMessage message = new NotificationMessage(phone, makeAuthenticateContent(randomNum));
        sendMessage(NotificationRequestDto.createFromMessages(makeMessages(message)));
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
    @Override
    public String makeRandomNumber() {
        int number = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);
        return Integer.toString(number);
    }

    private String makeAuthenticateContent(String number) {
        return String.format(AUTH_PHONE, number);
    }

    private boolean checkDuplicatePhone(String phone) {
        return userRepository.existsUserByPhonenum(phone);
    }
}
