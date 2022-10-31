package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationViewRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
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
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationRequest(reservationRequestDto)), HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> reservationManage(@PathVariable("scheduleId") Long id, @RequestParam String nickname, @RequestBody ReservationManageRequestDto reservationManageRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationManage(id, nickname, reservationManageRequestDto)), HttpStatus.OK);

    }

    @PostMapping("/block")
    public ResponseEntity<CommonResponse> reservationBlock(@RequestBody ReservationBlockRequestDto reservationBlockRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationBlock(reservationBlockRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> reservationView(@PathVariable("nickname") String nickname, @RequestBody ReservationViewRequestDto reservationViewRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationView(nickname, reservationViewRequestDto)), HttpStatus.OK);
    }
}