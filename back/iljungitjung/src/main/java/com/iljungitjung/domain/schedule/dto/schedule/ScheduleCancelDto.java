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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd:hh:mm aa");
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.startDate=simpleDateFormat.format(schedule.getStartDate());
        this.endDate=simpleDateFormat.format(schedule.getEndDate());
        this.reason=schedule.getReason();
        this.cancelFrom=schedule.getCancelFrom();
        this.contents=schedule.getContents();
        this.phonenum=schedule.getPhonenum();

    }
}
