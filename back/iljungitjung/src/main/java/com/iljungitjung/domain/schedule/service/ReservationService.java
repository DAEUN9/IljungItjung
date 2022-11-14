package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.*;

import javax.servlet.http.HttpSession;

public interface ReservationService {
    ReservationIdResponseDto reservationRequest(ReservationRequestDto reservationRequestDto, HttpSession httpSession);
    ReservationIdResponseDto reservationManage(Long id, ReservationManageRequestDto reservationManageRequestDto, HttpSession httpSession);

    ReservationIdResponseDto reservationDelete(Long id, String reason, HttpSession httpSession);
    ReservationIdResponseDto reservationBlock(ReservationBlockRequestDto reservationBlockRequestDto, HttpSession httpSession);

    ReservationViewResponseDto reservationView(String startDate, String endDate, HttpSession httpSession);


}
