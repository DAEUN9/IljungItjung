package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;

import java.text.ParseException;

public interface ReservationService {
    void reservationRequest(ReservationRequestDto reservationRequestDto);
}
