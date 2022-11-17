package com.iljungitjung.domain.schedule.dto.schedule;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
public class ScheduleViewDetailResponseDto {

    private final Long id;
    private final String categoryName;

    private final String contents;
    private final String startDate;
    private final String endDate;
    private final String phonenum;

    public ScheduleViewDetailResponseDto(Schedule schedule){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd:hh:mm aa");
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.contents=schedule.getContents();
        this.startDate=simpleDateFormat.format(schedule.getStartDate());
        this.endDate=simpleDateFormat.format(schedule.getEndDate());
        this.phonenum=schedule.getPhonenum();
    }
}
