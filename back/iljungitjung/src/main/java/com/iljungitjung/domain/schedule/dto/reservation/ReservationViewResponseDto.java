package com.iljungitjung.domain.schedule.dto.reservation;


import lombok.Getter;

import java.util.List;

@Getter
public class ReservationViewResponseDto {

    private final List<ReservationViewDto> reservationViewDtoList;

    public ReservationViewResponseDto(List<ReservationViewDto> reservationViewDtoList) {
        this.reservationViewDtoList=reservationViewDtoList;
    }
}
