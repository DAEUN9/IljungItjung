package com.iljungitjung.domain.schedule.dto;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleViewDetailResponseDto {

    private final Long id;
    private final String categoryName;

    private final String contents;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String phonenum;

    public ScheduleViewDetailResponseDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.contents=schedule.getContents();
        this.date=schedule.getDate();
        this.startTime=schedule.getStartTime();
        this.endTime=schedule.getEndTime();
        this.phonenum=schedule.getPhonenum();
    }
}
