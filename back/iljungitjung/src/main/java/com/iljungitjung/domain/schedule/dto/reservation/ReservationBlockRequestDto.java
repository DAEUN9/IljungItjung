package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ReservationBlockRequestDto {

    private String userFromNickname;
    private String title;
    private String contents;

    @Size(min=8, max=8, message = "형식을 맞춰주세요 (ex.20221017)")
    private String date;

    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.1500)")
    private String startTime;

    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.1500)")
    private String endTime;

    public Schedule toScheduleEntity(ReservationBlockRequestDto reservationBlockRequestDto, Date startDate, Date endDate) {
        return Schedule.builder()
                .categoryName(reservationBlockRequestDto.getTitle())
                .contents(reservationBlockRequestDto.getContents())
                .startDate(startDate)
                .endDate(endDate)
                .type(Type.BLOCK)
                .build();
    }

}
