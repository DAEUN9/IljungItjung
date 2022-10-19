package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> scheduleView(@PathVariable("nickname") String nickname){
        //String id = (String) request.getAttribute("id");
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname)), HttpStatus.OK);
    }

}
