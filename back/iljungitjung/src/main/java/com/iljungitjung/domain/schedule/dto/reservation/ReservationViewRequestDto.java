package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationViewRequestDto {

    private String startDate;
    private String endDate;

}
