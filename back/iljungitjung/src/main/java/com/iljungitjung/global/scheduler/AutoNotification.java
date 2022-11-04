package com.iljungitjung.global.scheduler;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
public class AutoNotification {
    private final NotificationService notificationService;
    private final String ACCEPT_BASE = "일정있정에서 안내드립니다.\n[%s]\n%s\n%s - %s\n[%s]예약이 승인되었습니다.";
    private final String CANCEL_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n[%s]님의 예약 신청이 취소되었습니다.\n";
    private final String REFUSE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]\n [%s] 예약 신청이 거절되었습니다.\n";
    private final String DELETE_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]의 [%s]예약이 취소되었습니다.\n";
    private final String REQUEST_BASE = "일정있정에서 안내드립니다.\n%s\n%s - %s\n[%s]님이 [%s]예약을 신청 하셨습니다.\n홈페이지에서 확인해주세요.";

    public void buildTemplate(Schedule schedule) {
        NotificationMessageDto message = makeMessage(schedule);
        List<NotificationMessageDto> messageList = new ArrayList<>();
//        NotificationMessageDto message = NotificationMessageDto.builder()
//                .to(schedule.getPhonenum())
//                .content(content)
//                .build();
        messageList.add(message);
        NotificationRequestDto requestDto = new NotificationRequestDto(messageList);
        notificationService.sendMessage(requestDto);
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
            return new NotificationMessageDto(schedule.getUserTo().getPhonenum(), String.format(CANCEL_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));
        }
        if (schedule.getType().equals(Type.ACCEPT)) {
            return new NotificationMessageDto(schedule.getPhonenum(), String.format(ACCEPT_BASE, date, startTime, endTime, schedule.getUserTo().getNickname(), schedule.getCategoryName()));
        }
        return new NotificationMessageDto(schedule.getPhonenum(), String.format(DELETE_BASE, date, startTime, endTime, schedule.getUserFrom().getNickname(), schedule.getCategoryName()));

    }

}
