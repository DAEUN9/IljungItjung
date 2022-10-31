package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.*;

public interface ReservationService {
    ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto);
    ReservationIdResponseDto reservationManage(Long id, String nickname, ReservationManageRequestDto reservationManageRequestDto);

    ReservationIdResponseDto reservationDelete(Long id, String reason);
    ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto);

    ReservationViewResponseDto reservationView(String nickname, String startDate, String endDate);


}
