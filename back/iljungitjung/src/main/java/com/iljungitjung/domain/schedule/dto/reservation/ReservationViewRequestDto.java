package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationViewRequestDto {

    private String startDate;
    private String endDate;

}
