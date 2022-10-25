package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;

public interface ReservationService {
    void reservationRequest(ReservationRequestDto reservationRequestDto);
    void reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto);

}
