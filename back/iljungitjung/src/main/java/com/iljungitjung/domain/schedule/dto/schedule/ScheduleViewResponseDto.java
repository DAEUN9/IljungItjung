package com.iljungitjung.domain.schedule.dto.schedule;


import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleViewResponseDto {

    private final List<ScheduleViewDto> requestList;
    private final List<ScheduleViewDto> acceptedList;

    public ScheduleViewResponseDto(List<ScheduleViewDto> requestList, List<ScheduleViewDto> acceptedList) {
        this.requestList=requestList;
        this.acceptedList=acceptedList;
    }
}
