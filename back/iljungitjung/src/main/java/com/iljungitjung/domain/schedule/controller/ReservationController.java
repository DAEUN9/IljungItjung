package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CommonResponse> reservationRequest(@RequestBody ReservationRequestDto reservationRequestDto){
        reservationService.reservationRequest(reservationRequestDto);
        return ResponseEntity.ok().build();
    }

}