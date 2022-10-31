package com.iljungitjung.domain.schedule.dto.schedule;


import com.iljungitjung.domain.category.dto.CategoryViewResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleViewResponseDto {

    private final List<CategoryViewResponseDto> categoryList;
    private final List<ScheduleViewDto> requestList;
    private final List<ScheduleViewDto> acceptList;

    private final List<ScheduleBlockDto> blockList;

    private final List<ScheduleCancelDto> cancelList;

    public ScheduleViewResponseDto(List<CategoryViewResponseDto> categoryList, List<ScheduleViewDto> requestList, List<ScheduleViewDto> acceptList, List<ScheduleBlockDto> blockList, List<ScheduleCancelDto> cancelList) {
        this.categoryList = categoryList;
        this.requestList=requestList;
        this.acceptList=acceptList;
        this.blockList=blockList;
        this.cancelList=cancelList;
    }
}
