package com.iljungitjung.domain.schedule.dto;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleViewResponseDto {

    private final Long id;
    private final String categoryName;
    private final String color;
    private final String date;
    private final String startTime;
    private final String endTime;

    public ScheduleViewResponseDto(Schedule schedule, String color){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.color=color;
        this.date=schedule.getDate();
        this.startTime=schedule.getStartTime();
        this.endTime=schedule.getEndTime();
    }
}
