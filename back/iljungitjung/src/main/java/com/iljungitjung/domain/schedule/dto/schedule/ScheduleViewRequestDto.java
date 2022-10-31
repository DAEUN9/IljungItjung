package com.iljungitjung.domain.schedule.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleViewRequestDto {

    private boolean isMyView;
    private String startDate;
    private String endDate;

}
