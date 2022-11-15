package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.Getter;

@Getter
public class ReservationIdResponseDto {
    private final Long id;

    public ReservationIdResponseDto(Long id) {
        this.id=id;
    }
}
