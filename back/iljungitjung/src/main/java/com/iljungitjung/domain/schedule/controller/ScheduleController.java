package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.persistence.Column;
import javax.servlet.http.HttpSession;
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
                                                       @RequestParam(value = "startDate", required = false) String startDate,
                                                       @RequestParam(value = "endDate", required = false) String endDate, HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname, startDate, endDate, httpSession)), HttpStatus.OK);
    }

    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<CommonResponse> scheduleViewDetail(@PathVariable("scheduleId") Long scheduleId){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleViewDetail(scheduleId)), HttpStatus.OK);
    }

}
