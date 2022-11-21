package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.Getter;

@Getter
public class ReservationBlockResponseDto {

    private final Long count;

    public ReservationBlockResponseDto(Long count) {
        this.count=count;
    }
}
