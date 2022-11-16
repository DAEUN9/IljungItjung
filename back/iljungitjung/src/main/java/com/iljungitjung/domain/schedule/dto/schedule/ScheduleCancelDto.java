package com.iljungitjung.domain.schedule.dto.schedule;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
public class ScheduleCancelDto {

    private final Long id;
    private final String categoryName;
    private final String startDate;
    private final String endDate;

    private final String reason;
    private final String cancelFrom;
    private final String contents;

    private final String phonenum;


    public ScheduleCancelDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
        this.reason=schedule.getReason();
        this.cancelFrom=schedule.getCancelFrom();
        this.contents=schedule.getContents();
        this.phonenum=schedule.getPhonenum();

    }
}
