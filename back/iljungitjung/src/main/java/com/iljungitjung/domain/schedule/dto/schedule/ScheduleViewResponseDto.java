package com.iljungitjung.domain.schedule.dto.schedule;


import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleViewResponseDto {

    private final List<ScheduleViewDto> requestList;
    private final List<ScheduleViewDto> acceptList;

    private final List<ScheduleBlockDto> blockList;

    private final List<ScheduleCancelDto> cancelList;

    public ScheduleViewResponseDto(List<ScheduleViewDto> requestList, List<ScheduleViewDto> acceptList, List<ScheduleBlockDto> blockList, List<ScheduleCancelDto> cancelList) {
        this.requestList=requestList;
        this.acceptList=acceptList;
        this.blockList=blockList;
        this.cancelList=cancelList;
    }
}
