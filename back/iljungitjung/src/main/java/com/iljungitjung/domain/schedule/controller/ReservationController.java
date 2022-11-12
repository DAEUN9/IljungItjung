package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@RequiredArgsConstructor
@RequestMapping("/reservations")
@RestController
@Validated
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<CommonResponse> reservationRequest(@RequestBody @Valid ReservationRequestDto reservationRequestDto
            , HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationRequest(reservationRequestDto, httpSession)), HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<CommonResponse> reservationManage(
            @Pattern(regexp = "^[0-9]+$", message = "scheduleId는 숫자만 입력가능합니다.")
            @PathVariable("scheduleId") Long id,

            @RequestBody @Valid ReservationManageRequestDto reservationManageRequestDto
            , HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationManage(id, reservationManageRequestDto, httpSession)), HttpStatus.OK);

    }

    @DeleteMapping("/{scheduleId}")
    public void reservationDelete(
            @Pattern(regexp = "^[0-9]+$", message = "scheduleId는 숫자만 입력가능합니다.")
            @PathVariable("scheduleId") Long id,

            @RequestParam String reason
            , HttpSession httpSession){
        reservationService.reservationDelete(id,reason, httpSession);

    }

    @PostMapping("/block")
    public ResponseEntity<CommonResponse> reservationBlock(@RequestBody @Valid ReservationBlockRequestDto reservationBlockRequestDto
            , HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationBlock(reservationBlockRequestDto, httpSession)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponse> reservationView(@Size(min=8, max=8, message = "startDate 형식을 맞춰주세요 (ex.20221017)")
                                                          @Pattern(regexp = "^[0-9]+$", message = "startDate는 숫자만 입력가능합니다.")
                                                          @RequestParam("startDate") String startDate,

                                                          @Size(min=8, max=8, message = "endDate 형식을 맞춰주세요 (ex.20221017)")
                                                          @Pattern(regexp = "^[0-9]+$", message = "endDate는 숫자만 입력가능합니다.")
                                                          @RequestParam("endDate") String endDate,

                                                          HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(reservationService.reservationView(startDate, endDate, httpSession)), HttpStatus.OK);
    }
}