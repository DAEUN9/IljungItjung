package com.iljungitjung.domain.schedule.dto.schedule;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ScheduleViewResponseDto {

    private final Long id;
    private final String categoryName;
    private final String color;
    private final String startDate;
    private final String endDate;

    public ScheduleViewResponseDto(Schedule schedule, String color){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd:hh:mm");
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.color=color;
        this.startDate=simpleDateFormat.format(schedule.getStartDate());
        this.endDate=simpleDateFormat.format(schedule.getEndDate());
    }
}
