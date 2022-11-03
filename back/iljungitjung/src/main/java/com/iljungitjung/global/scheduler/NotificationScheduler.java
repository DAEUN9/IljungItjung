package com.iljungitjung.global.scheduler;

import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationSchedulerDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class NotificationScheduler {
    private final ScheduleRepository scheduleRepository;
    private final NotificationService notificationService;
    final String BASE_MESSAGE = "일정있정에서 안내드립니다.\n";

    @Scheduled(cron=" 0 0 10 ? * * ")
    private void searchTodaySchedules() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        Date startToday = java.sql.Timestamp.valueOf(startDatetime);
        Date endToday = java.sql.Timestamp.valueOf(endDatetime);
        List<Schedule> todayScheduleList = scheduleRepository.findByStartDateBetween(startToday, endToday);
        HashMap<String, List<NotificationSchedulerDto>> phoneHashMap = new HashMap<>();
        List<NotificationSchedulerDto> emptyScheduleList = new ArrayList<>();
        for (Schedule schedule : todayScheduleList) {
            if (!(schedule.getType().equals(Type.ACCEPT))) {
                continue;
            }
            String phone = schedule.getPhonenum();
            NotificationSchedulerDto todaySchedule = new NotificationSchedulerDto(schedule);
            List<NotificationSchedulerDto> tempScheduleList = phoneHashMap.getOrDefault(phone, emptyScheduleList);
            tempScheduleList.add(todaySchedule);
            phoneHashMap.put(phone, tempScheduleList);
        }
        for (Map.Entry<String, List<NotificationSchedulerDto>> object : phoneHashMap.entrySet()) {
            sendTodaySchedules(object.getKey(), object.getValue());
        }
    }

    private void sendTodaySchedules(String phone, List<NotificationSchedulerDto> scheduleList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_MESSAGE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
        for (NotificationSchedulerDto schedule : scheduleList) {
            String time = simpleDateFormat.format(schedule.getStartDate());
            String extraContent = String.format("[%s]\n%s\n%s\n", schedule.getUserTo(), time, schedule.getCategoryName());
            stringBuilder.append(extraContent);
        }
        List<NotificationMessageDto> messageList = new ArrayList<>();
        NotificationMessageDto message = NotificationMessageDto.builder()
                .to(phone)
                .content(stringBuilder.toString())
                .build();
        messageList.add(message);
        NotificationRequestDto requestDto = NotificationRequestDto.builder()
                .messages(messageList)
                .content("필수")
                .build();
        notificationService.sendMessage(requestDto);
    }
}
