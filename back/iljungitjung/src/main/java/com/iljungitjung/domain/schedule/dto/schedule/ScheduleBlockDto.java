package com.iljungitjung.domain.schedule.dto.schedule;

import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleBlockDto {

    private final Long id;
    private final String startDate;
    private final String endDate;

    public ScheduleBlockDto(Schedule schedule){
        this.id=schedule.getId();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
    }
}
