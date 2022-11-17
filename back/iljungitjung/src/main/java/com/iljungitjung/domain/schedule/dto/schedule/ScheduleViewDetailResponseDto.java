package com.iljungitjung.domain.schedule.dto.schedule;

import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleViewDetailResponseDto {

    private final Long id;
    private final String categoryName;
    private final String contents;
    private final String startDate;
    private final String endDate;
    private final String phonenum;

    public ScheduleViewDetailResponseDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.contents=schedule.getContents();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
        this.phonenum=schedule.getPhonenum();
    }
}
