package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ReservationManageRequestDto {

    @NotNull(message = "accept는 true 또는 false 입니다.")
    private boolean accept;

    private String reason;

}
