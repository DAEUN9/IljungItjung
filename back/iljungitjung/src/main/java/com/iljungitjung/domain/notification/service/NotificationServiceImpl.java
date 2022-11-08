package com.iljungitjung.domain.notification.service;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationMessageRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.exception.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    @Value("${message.ncloud.service_id}")
    private String NCLOUD_SERVICE_ID;
    @Value("${message.ncloud.access_key}")
    private String NCLOUD_ACCESS_KEY;
    @Value("${message.ncloud.secret_key}")
    private String NCLOUD_SECRET_KEY;
    @Value("${message.ncloud.phone}")
    private String SENDER_PHONE;
    private final String SPACE = " ";
    private final String NEWLINE = "\n";
    private final String METHOD = "POST";
    private final String SIGNATURE_URL = "/sms/v2/services/";
    private final String MESSAGE_REQUEST_URL = "https://sens.apigw.ntruss.com/sms/v2/services/";

    @Override
    public NotificationResponseDto sendMessage(NotificationRequestDto requestDto) {
        Long time = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = makeHeaders(time);
        NotificationMessageRequestDto jsonBody = new NotificationMessageRequestDto(requestDto, SENDER_PHONE);
        HttpEntity<NotificationMessageRequestDto> body = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<NotificationResponseDto> responseEntity = restTemplate.exchange(MESSAGE_REQUEST_URL + NCLOUD_SERVICE_ID + "/messages", HttpMethod.POST, body, NotificationResponseDto.class);
        checkStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    private HttpHeaders makeHeaders(Long time) {
        HttpHeaders headers = new HttpHeaders();
        String signature = makeSignature(time);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", NCLOUD_ACCESS_KEY);
        headers.set("x-ncp-apigw-signature-v2", signature);
        return headers;
    }

    private String makeSignature(Long time) {
        String timeStamp = time.toString();
        SecretKeySpec signingKey;
        Mac mac;
        byte[] rawHmac = new byte[0];

        String message = new StringBuilder()
                .append(METHOD)
                .append(SPACE)
                .append(SIGNATURE_URL + NCLOUD_SERVICE_ID +"/messages")
                .append(NEWLINE)
                .append(timeStamp)
                .append(NEWLINE)
                .append(NCLOUD_ACCESS_KEY)
                .toString();
        try {
            signingKey = new SecretKeySpec(NCLOUD_SECRET_KEY.getBytes("UTF-8"), "HmacSHA256");

            mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new FailSignatureKeyErrorException();
        }
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    private void checkStatus (HttpStatus status) {
        if (!(status.is2xxSuccessful())) {
            throw new FailSendMessageException();
        }
    }

    private final String ACCEPT_BASE = "일정있정에서 안내드립니다.\n[%s]\n%s\n%s - %s\n[%s]예약이 승인되었습니다.";
    private final String CANCEL_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n[%s]님의 예약 신청이 취소되었습니다.\n";
    private final String REFUSE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n [%s] 예약 신청이 거절되었습니다.\n";
    private final String DELETE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]의 [%s]예약이 취소되었습니다.\n";
    private final String REQUEST_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]님이 [%s]예약을 신청 하셨습니다.\n홈페이지에서 확인해주세요.";

    @Override
    public void buildTemplate(Schedule schedule) {
        NotificationMessageDto message = makeMessage(schedule);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(message);
        NotificationRequestDto requestDto = new NotificationRequestDto(messageList);
        sendMessage(requestDto);
    }

    private NotificationMessageDto makeMessage(Schedule schedule) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String date = dateFormat.format(schedule.getStartDate());
        String startTime = timeFormat.format(schedule.getStartDate());
        String endTime = timeFormat.format(schedule.getEndDate());
        if (schedule.getType().equals(Type.CANCEL)) {
            if (schedule.getCancelFrom().equals("제공자")) {
                return new NotificationMessageDto(schedule.getPhonenum(), String.format(REFUSE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));
            }
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
}
