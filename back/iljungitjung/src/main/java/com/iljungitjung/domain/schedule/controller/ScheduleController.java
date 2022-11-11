package com.iljungitjung.domain.schedule.controller;

import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.global.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.Nullable;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@RequestMapping("/schedules")
@RestController
@Validated
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{nickname}")
    public ResponseEntity<CommonResponse> scheduleView(@Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
                                                       @PathVariable("nickname") String nickname,

                                                       @Size(min=8, max=8, message = "startDate 형식을 맞춰주세요 (ex.20221017)")
                                                       @Pattern(regexp = "^[0-9]+$", message = "startDate는 숫자만 입력가능합니다.")
                                                       @RequestParam(value = "startDate", required = false) String startDate,

                                                       @Size(min=8, max=8, message = "endDate 형식을 맞춰주세요 (ex.20221017)")
                                                       @Pattern(regexp = "^[0-9]+$", message = "endDate는 숫자만 입력가능합니다.")
                                                       @RequestParam(value = "endDate", required = false) String endDate,

                                                       HttpSession httpSession){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleView(nickname, startDate, endDate, httpSession)), HttpStatus.OK);
    }

    @GetMapping("/detail/{scheduleId}")
    public ResponseEntity<CommonResponse> scheduleViewDetail(
            @Pattern(regexp = "^[0-9]+$", message = "scheduleId는 숫자만 입력가능합니다.")
            @PathVariable("scheduleId") Long scheduleId){
        return new ResponseEntity<>(CommonResponse.getSuccessResponse(scheduleService.scheduleViewDetail(scheduleId)), HttpStatus.OK);
    }

}
