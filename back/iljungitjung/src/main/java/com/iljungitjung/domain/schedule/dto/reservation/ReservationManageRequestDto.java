package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationManageRequestDto {

    private boolean accept;

    private String reason;

}
