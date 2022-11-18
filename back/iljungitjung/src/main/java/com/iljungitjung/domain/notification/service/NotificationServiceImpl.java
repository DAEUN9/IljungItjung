package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.entity.Auto;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.schedule.entity.Schedule;

import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;

    private final NotificationCorrespondence notificationCorrespondence;

    @Override
    public NotificationResponseDto sendMessage(NotificationRequestDto requestDto) {
        HttpEntity<NotificationMessageRequestDto> body = makeBody(requestDto);
        NotificationResponseDto responseDto = notificationCorrespondence.sendNcloud(body);
        return checkStatusEqualsAccpeted(responseDto);
    }

    private HttpEntity<NotificationMessageRequestDto> makeBody(NotificationRequestDto requestDto) {
        HttpHeaders headers = notificationCorrespondence.makeHeaders();
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        return new HttpEntity<>(jsonBody, headers);
    }

    public NotificationResponseDto checkStatusEqualsAccpeted(NotificationResponseDto response){
        if(response.getStatusCode().equals(statusAccepted())) return response;
        throw new FailSendMessageException();
    }

    private String statusAccepted() {
        return Integer.toString(HttpStatus.ACCEPTED.value());
    }

    @Override
    public void autoReservationMessage(Schedule schedule) {
        NotificationMessage message = makeMessage(schedule);
        message.checkExistPhonenum();
        List<NotificationMessage> messageList = makeMessageList(message);
        NotificationRequestDto requestDto = NotificationRequestDto.createFromMessages(messageList);
        sendMessage(requestDto);
    }

    private List<NotificationMessage> makeMessageList(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private NotificationMessage makeMessage(Schedule schedule) {
        Auto auto = Auto.getMatchAuto(schedule.getType(), schedule.getCancelFrom());
        String content = auto.makeContent(schedule);
        String phonenum = auto.getPhonenum(schedule);
        return new NotificationMessage(phonenum, content);
    }


}
