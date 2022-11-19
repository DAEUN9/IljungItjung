package com.iljungitjung.global.scheduler;

import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
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
    final private String BASE_MESSAGE = "일정있정에서 오늘 일정 안내드립니다.\n";
    final private String SCHEDULE_GUIDE = "[%s]\n%s - %s\n%s\n";

    @Scheduled(cron=" 0 0 20 ? * * ", zone = "Asia/Seoul")
    private void searchTodaySchedules() {
        Date startToday = setTodayTime(0, 0, 0);
        Date endToday = setTodayTime(23, 59, 59);
        List<Schedule> todayScheduleList = scheduleRepository.findByStartDateBetween(startToday, endToday);
        HashMap<String, List<Schedule>> phoneHashMap = new HashMap<>();
        List<Schedule> emptyScheduleList = new ArrayList<>();
        for (Schedule schedule : todayScheduleList) {
            if (!checkAccepted(schedule)) continue;
            String phone = schedule.getPhonenum();
            List<Schedule> tempScheduleList = phoneHashMap.getOrDefault(phone, emptyScheduleList);
            tempScheduleList.add(schedule);
            phoneHashMap.put(phone, tempScheduleList);
        }
        for (Map.Entry<String, List<Schedule>> object : phoneHashMap.entrySet()) {
            sendTodaySchedules(object.getKey(), object.getValue());
        }
    }

    private Date setTodayTime(int h, int m, int s) {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(h,m,s));
        return java.sql.Timestamp.valueOf(startDatetime);
    }

    private boolean checkAccepted(Schedule schedule) {
        if (schedule.getType().equals(Type.ACCEPT)) return true;
        return false;
    }

    private void sendTodaySchedules(String phone, List<Schedule> scheduleList) {
        NotificationMessage message = new NotificationMessage(phone, makeContents(scheduleList));
        NotificationRequestDto requestDto = NotificationRequestDto.createFromMessages(makeMessageList(message));

        notificationService.sendMessage(requestDto);
    }

    private List<NotificationMessage> makeMessageList(NotificationMessage... message){
        return Arrays.asList(message);
    }

    private String makeContents(List<Schedule> scheduleList)  {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_MESSAGE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        for (Schedule schedule : scheduleList) {
            String startTime = simpleDateFormat.format(schedule.getStartDate());
            String endTime = simpleDateFormat.format(schedule.getEndDate());
            String extraContent = String.format(SCHEDULE_GUIDE, schedule.getUserTo().getNickname(), startTime, endTime, schedule.getCategoryName());
            stringBuilder.append(extraContent);
        }
        return stringBuilder.toString();
    }
}
