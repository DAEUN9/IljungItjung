package com.iljungitjung.domain.schedule.dto.schedule;

import com.iljungitjung.domain.category.dto.CategoryViewResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleViewResponseDto {

    private final List<CategoryViewResponseDto> categoryList;
    private final List<ScheduleViewDto> requestList;
    private final List<ScheduleViewDto> acceptList;
    private final List<ScheduleBlockDto> blockList;
    private final List<ScheduleCancelDto> cancelList;
    private final List<Boolean> blockDayList;

    public ScheduleViewResponseDto(List<CategoryViewResponseDto> categoryList, List<ScheduleViewDto> requestList, List<ScheduleViewDto> acceptList, List<ScheduleBlockDto> blockList, List<ScheduleCancelDto> cancelList, List<Boolean> blockDayList) {
        this.categoryList = categoryList;
        this.requestList=requestList;
        this.acceptList=acceptList;
        this.blockList=blockList;
        this.cancelList=cancelList;
        this.blockDayList=blockDayList;
    }
}
