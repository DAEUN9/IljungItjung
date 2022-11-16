package com.iljungitjung.domain.notification.dto;

import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.util.Date;

@Getter
public class NotificationSchedulerDto {
    private Date startDate;
    private Date endDate;
    private String userTo;
    private String categoryName;

    public NotificationSchedulerDto(Schedule schedule) {
        startDate = schedule.getStartDate();
        endDate = schedule.getEndDate();
        userTo = schedule.getUserTo().getNickname();
        categoryName = schedule.getCategoryName();
    }
}
