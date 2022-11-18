package com.iljungitjung.domain.schedule.dto.reservation;


import lombok.Getter;

import java.util.List;

@Getter
public class ReservationViewResponseDto {

    private final List<ReservationViewDto> reservationViewDtoList;
    private final List<ReservationCancelViewDto> reservationCancelViewDtoList;

    public ReservationViewResponseDto(List<ReservationViewDto> reservationViewDtoList, List<ReservationCancelViewDto> reservationCancelViewDtoList) {
        this.reservationViewDtoList=reservationViewDtoList;
        this.reservationCancelViewDtoList=reservationCancelViewDtoList;
    }
}
