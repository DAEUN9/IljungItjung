package com.iljungitjung.domain.schedule.dto.reservation;


import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
public class ReservationViewDto {

    private final Long id;
    private final String categoryName;
    private final String color;
    private final String startDate;
    private final String endDate;
    private final String imagePath;
    private final String nickname;
    private final String contents;
    private final Type type;

    public ReservationViewDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.color= schedule.getColor();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
        this.imagePath=schedule.getUserTo().getImagePath();
        this.nickname=schedule.getUserTo().getNickname();
        this.contents=schedule.getContents();
        this.type=schedule.getType();
    }
}
