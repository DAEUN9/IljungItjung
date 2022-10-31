package com.iljungitjung.domain.schedule.dto.reservation;


import com.iljungitjung.domain.schedule.dto.schedule.ScheduleBlockDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleCancelDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationViewResponseDto {

    private final List<ReservationViewDto> requestList;
    private final List<ReservationViewDto> acceptList;

    private final List<ReservationCancelViewDto> cancelList;

    public ReservationViewResponseDto(List<ReservationViewDto> requestList, List<ReservationViewDto> acceptList, List<ReservationCancelViewDto> cancelList) {
        this.requestList=requestList;
        this.acceptList=acceptList;
        this.cancelList=cancelList;
    }
}