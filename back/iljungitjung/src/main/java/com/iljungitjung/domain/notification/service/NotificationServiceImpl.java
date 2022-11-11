package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.global.scheduler.NotificationNcloud;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;
    private final String ACCEPT_BASE = "일정있정에서 안내드립니다.\n[%s]\n%s\n%s - %s\n[%s]예약이 승인되었습니다.";
    private final String CANCEL_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n[%s]님의 예약 신청이 취소되었습니다.\n";
    private final String REFUSE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n [%s] 예약 신청이 거절되었습니다.\n";
    private final String DELETE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]의 [%s]예약이 취소되었습니다.\n";
    private final String REQUEST_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]님이 [%s]예약을 신청 하셨습니다.\n홈페이지에서 확인해주세요.";
    private final NotificationNcloud notificationNcloud;

    @Override
    public NotificationResponseDto sendMessage(NotificationRequestDto requestDto) {
        HttpEntity<NotificationMessageRequestDto> body = makeBody(requestDto);
        return checkStatusEqualsAccpeted(notificationNcloud.sendNcloud(body));
    }

    private HttpEntity<NotificationMessageRequestDto> makeBody(NotificationRequestDto requestDto) {
        HttpHeaders headers = notificationNcloud.makeHeaders();
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        return new HttpEntity<>(jsonBody, headers);
    }

    public NotificationResponseDto checkStatusEqualsAccpeted(NotificationResponseDto response){
        if(response.getStatusCode().equals(HttpStatus.ACCEPTED.toString())) return response;
        throw new FailSendMessageException();
    }

    @Override
    public void autoReservationMessage(Schedule schedule) {
        NotificationMessageDto message = makeMessage(schedule);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(message);
        NotificationRequestDto requestDto = new NotificationRequestDto(messageList);
        sendMessage(requestDto);
    }

    private NotificationMessageDto makeMessage(Schedule schedule) {
        String date = makeDateFormat(schedule.getStartDate());
        String startTime = makeTimeFormat(schedule.getStartDate());
        String endTime = makeTimeFormat(schedule.getEndDate());
        if (schedule.getType().equals(Type.CANCEL) && schedule.getCancelFrom().equals("제공자")) {
            return new NotificationMessageDto(schedule.getPhonenum(), String.format(REFUSE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));
        }
        if (schedule.getType().equals(Type.CANCEL) && schedule.getCancelFrom().equals("사용자")) {
            return new NotificationMessageDto(schedule.getUserTo().getPhonenum(), String.format(CANCEL_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getUserTo().getNickname()));
        }
        if (schedule.getType().equals(Type.ACCEPT)) {
            return new NotificationMessageDto(schedule.getPhonenum(), String.format(ACCEPT_BASE, schedule.getUserTo().getNickname(), date, startTime, endTime, schedule.getCategoryName()));
        }
        if (schedule.getType().equals(Type.REQUEST)) {
            return new NotificationMessageDto(schedule.getUserTo().getPhonenum(), String.format(REQUEST_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));
        }
        return new NotificationMessageDto(schedule.getPhonenum(), String.format(DELETE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));
    }

    private String makeDateFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("yyyy-MM-dd");
        return base.format(date);
    }

    private String makeTimeFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("HH:mm");
        return base.format(date);
    }

}
