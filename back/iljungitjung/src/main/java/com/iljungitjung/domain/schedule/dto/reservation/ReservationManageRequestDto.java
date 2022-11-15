package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationManageRequestDto {

    @NotNull(message = "accept는 true 또는 false 입니다.")
    private boolean accept;

    @Size(min = 0, max = 100, message = "한줄소개는 최소 0자, 최대 100자만 가능합니다.")
    private String reason;

}
