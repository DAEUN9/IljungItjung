package com.iljungitjung.domain.schedule.dto.reservation;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBlockListRequestDto {

    private List<Boolean> days;
    private List<ReservationBlockDto> blockList;
}
