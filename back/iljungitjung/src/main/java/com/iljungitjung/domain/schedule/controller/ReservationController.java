package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CommonResponse> reservationRequest(@RequestBody @Valid ReservationRequestDto reservationRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationRequest(reservationRequestDto)), HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> reservationManage(@PathVariable("scheduleId") Long id, @RequestParam String nickname, @RequestBody @Valid ReservationManageRequestDto reservationManageRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationManage(id, nickname, reservationManageRequestDto)), HttpStatus.OK);

    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> reservationDelete(@PathVariable("scheduleId") Long id, @RequestParam String reason){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationDelete(id,reason)), HttpStatus.OK);

    }

    @PostMapping("/block")
    public ResponseEntity<CommonResponse> reservationBlock(@RequestBody @Valid ReservationBlockRequestDto reservationBlockRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationBlock(reservationBlockRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> reservationView(@PathVariable("nickname") String nickname,
                                                          @NotBlank(message = "startDate는 비워둘 수 없습니다.")
                                                          @Size(min=8, max=8)
                                                          @RequestParam("startDate") String startDate,
                                                          @NotBlank(message = "endDate는 비워둘 수 없습니다.")
                                                              @Size(min=8, max=8)
                                                              @RequestParam("endDate") String endDate){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationView(nickname, startDate, endDate)), HttpStatus.OK);
    }
}