package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationManageRequestDto {

    @NotNull(message = "accept는 true 또는 false 입니다.")
    private boolean accept;

    private String reason;

}
