package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewRequestDto;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> scheduleView(@PathVariable("nickname") String nickname, @RequestBody ScheduleViewRequestDto scheduleViewRequestDto){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname, scheduleViewRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<CommonResponse> scheduleViewDetail(@PathVariable("id") Long id){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleViewDetail(id)), HttpStatus.OK);
    }

}
