package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;

public interface ReservationService {
    ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto);
    ReservationIdResponseDto reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto);

    ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto);
}
