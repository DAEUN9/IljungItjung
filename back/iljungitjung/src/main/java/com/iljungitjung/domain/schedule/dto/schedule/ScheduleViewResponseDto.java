package com.iljungitjung.domain.schedule.dto.schedule;


import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleViewResponseDto {

    private final List<ScheduleViewDto> requestList;
    private final List<ScheduleViewDto> acceptList;

    private final List<ScheduleBlockDto> blockList;

    public ScheduleViewResponseDto(List<ScheduleViewDto> requestList, List<ScheduleViewDto> acceptList, List<ScheduleBlockDto> blockList) {
        this.requestList=requestList;
        this.acceptList=acceptList;
        this.blockList=blockList;
    }
}
