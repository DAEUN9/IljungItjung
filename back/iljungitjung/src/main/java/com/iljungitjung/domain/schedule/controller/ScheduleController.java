package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> scheduleView(@PathVariable("nickname") String nickname,
                                                       @Size(min=8, max=8, message = "형식을 맞춰주세요 (ex.20221017)")
                                                       @RequestParam("startDate") String startDate,
                                                       @Size(min=8, max=8, message = "형식을 맞춰주세요 (ex.20221017)")
                                                       @RequestParam("endDate") String endDate,
                                                       @NotNull(message = "isMyView는 true 또는 false 입니다.")
                                                       @RequestParam("isMyView") boolean isMyView){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname, startDate, endDate, isMyView)), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CommonResponse> scheduleViewDetail(@PathVariable("id") Long id){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleViewDetail(id)), HttpStatus.OK);
    }

}