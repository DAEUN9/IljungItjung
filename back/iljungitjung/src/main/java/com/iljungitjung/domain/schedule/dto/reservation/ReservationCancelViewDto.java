package com.iljungitjung.domain.schedule.dto.reservation;


import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.Getter;

@Getter
public class ReservationCancelViewDto {

    private final Long id;
    private final String categoryName;
    private final String color;
    private final String startDate;
    private final String endDate;
    private final String imagePath;
    private final String nickname;
    private final String contents;
    private final Type type;
    private final String reason;
    private final String cancelFrom;

    public ReservationCancelViewDto(Schedule schedule){
        this.id=schedule.getId();
        this.categoryName=schedule.getCategoryName();
        this.color= schedule.getColor();
        this.startDate=schedule.getDateFormat(schedule.getStartDate());
        this.endDate= schedule.getDateFormat(schedule.getEndDate());
        this.imagePath=schedule.getUserTo().getImagePath();
        this.nickname=schedule.getUserTo().getNickname();
        this.contents=schedule.getContents();
        this.type=schedule.getType();
        this.reason=schedule.getReason();
        this.cancelFrom=schedule.getCancelFrom();
    }
}
