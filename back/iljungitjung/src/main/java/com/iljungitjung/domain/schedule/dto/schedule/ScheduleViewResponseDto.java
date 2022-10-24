package com.iljungitjung.domain.schedule.dto.schedule;


import com.iljungitjung.domain.schedule.entity.Schedule;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
