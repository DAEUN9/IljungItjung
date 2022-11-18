package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.notification.FailSendMessageException;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.global.scheduler.NotificationCorrespondence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    private final String TEMP_PHONE = "01000000000";
    private final NotificationCorrespondence notificationCorrespondence;

    @Override
    public NotificationResponseDto sendMessage(NotificationRequestDto requestDto) {
        HttpEntity<NotificationMessageRequestDto> body = makeBody(requestDto);
        NotificationResponseDto responseDto = notificationCorrespondence.sendNcloud(body);
        return checkStatusEqualsAccepted(responseDto);
    }

    private HttpEntity<NotificationMessageRequestDto> makeBody(NotificationRequestDto requestDto) {
        HttpHeaders headers = notificationCorrespondence.makeHeaders();
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        return new HttpEntity<>(jsonBody, headers);
    }

    public NotificationResponseDto checkStatusEqualsAccepted(NotificationResponseDto response){
        if(response.getStatusCode().equals(statusAccepted())) return response;
        throw new FailSendMessageException();
    }

    private String statusAccepted() {
        return Integer.toString(HttpStatus.ACCEPTED.value());
    }

    @Override
    public void autoReservationMessage(Schedule schedule) {
        NotificationMessage message = makeMessage(schedule);
        if (existPhoneNum(message)) {
            List<NotificationMessage> messageList = makeMessageList(message);
            NotificationRequestDto requestDto = NotificationRequestDto.createFromMessages(messageList);
            sendMessage(requestDto);
        }
    }

    private boolean existPhoneNum(NotificationMessage message) {
        if(message.getTo() == null)
            return false;
        return true;
    }

    private List<NotificationMessage> makeMessageList(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private NotificationMessage makeMessage(Schedule schedule) {
        if (schedule.getType().equals(Type.CANCEL) && schedule.getCancelFrom().equals("제공자")) {
            return new NotificationMessage(schedule.getPhonenum(), makeContents(schedule, 1));
        }
        if (schedule.getType().equals(Type.CANCEL) && schedule.getCancelFrom().equals("사용자")) {
            return new NotificationMessage(schedule.getUserTo().getPhonenum(), makeContents(schedule, 2));
        }
        if (schedule.getType().equals(Type.ACCEPT)) {
            return new NotificationMessage(schedule.getPhonenum(), makeContents(schedule, 3));
        }
        if (schedule.getType().equals(Type.REQUEST)) {
            return new NotificationMessage(schedule.getUserTo().getPhonenum(), makeContents(schedule, 4));
        }
        return new NotificationMessage(schedule.getPhonenum(), makeContents(schedule, 5));
    }

    private String makeDateFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("yyyy-MM-dd");
        return base.format(date);
    }

    private String makeTimeFormat(Date date) {
        SimpleDateFormat base = new SimpleDateFormat("HH:mm");
        return base.format(date);
    }

    private String makeContents(Schedule schedule, int idx) {
        String date = makeDateFormat(schedule.getStartDate());
        String startTime = makeTimeFormat(schedule.getStartDate());
        String endTime = makeTimeFormat(schedule.getEndDate());
        if (idx == 1) {
            return String.format(REFUSE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
        }
        if (idx == 2) {
            return String.format(CANCEL_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getUserTo().getNickname());
        }
        if (idx == 3) {
            return String.format(ACCEPT_BASE, schedule.getUserTo().getNickname(), date, startTime, endTime, schedule.getCategoryName());
        }
        if (idx == 4) {
            return String.format(REQUEST_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
        }
        return String.format(DELETE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName());
    }

}
