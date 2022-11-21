package com.iljungitjung.domain.schedule.dto.schedule;

import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleViewDto {

    private final Long id;
    private final String categoryName;
    private final String color;
    private final String startDate;
    private final String endDate;
    private final String contents;
    private final String phonenum;
    private final String nickname;
    private final String imagePath;

    public ScheduleViewDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.color= schedule.getColor();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
        this.contents=schedule.getContents();
        this.phonenum=schedule.getPhonenum();
        this.nickname=schedule.getUserFrom().getNickname();
        this.imagePath=schedule.getUserFrom().getImagePath();
    }
}
