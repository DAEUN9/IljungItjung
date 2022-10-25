package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
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

    @PutMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> reservationManage(@PathVariable("scheduleId") Long id, @RequestBody ReservationManageRequestDto reservationManageRequestDto){
        reservationService.reservationManage(id, reservationManageRequestDto);
        return ResponseEntity.ok().build();
    }
}