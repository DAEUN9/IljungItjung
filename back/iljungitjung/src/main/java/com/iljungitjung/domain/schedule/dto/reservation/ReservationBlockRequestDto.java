package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ReservationBlockRequestDto {

    private String userFromNickname;
    private String title;
    private String contents;
    private String date;
    private String startTime;
    private String endTime;

    public Schedule toScheduleEntity(ReservationBlockRequestDto reservationBlockRequestDto,  Date startDate, Date endDate) {
        return Schedule.builder()
                .categoryName(reservationBlockRequestDto.getTitle())
                .contents(reservationBlockRequestDto.getContents())
                .startDate(startDate)
                .endDate(endDate)
                .type(Type.BLOCK)
                .build();
    }

}
