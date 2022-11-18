package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
@Validated
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> scheduleView(@Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
                                                       @PathVariable("nickname") String nickname,

                                                       @Pattern(regexp = "^[0-9]{8}$", message = "startDate는 8자리 숫자만 입력가능합니다. (ex.20221017)")
                                                       @RequestParam(value = "startDate", required = false) String startDate,

                                                       @Pattern(regexp = "^[0-9]{8}$", message = "endDate는 8자리 숫자만 입력가능합니다. (ex.20221017)")
                                                       @RequestParam(value = "endDate", required = false) String endDate,

                                                       HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname, startDate, endDate, httpSession)), HttpStatus.OK);
    }

    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<CommonResponse> scheduleViewDetail(
            @PathVariable("scheduleId") Long scheduleId){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleViewDetail(scheduleId)), HttpStatus.OK);
    }

}
