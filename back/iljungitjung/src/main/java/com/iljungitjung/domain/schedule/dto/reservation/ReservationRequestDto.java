package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ReservationRequestDto {

    private String userFromNickname;

    private String userToNickname;

    private String date;
    private String startTime;
    private String contents;
    private String phone;
    private String categoryName;

    public Schedule toScheduleEntity(ReservationRequestDto reservationRequestDto, Date startDate, Date endDate, String color, Type type) {
        return Schedule.builder()
                .userFromId(reservationRequestDto.getUserFromNickname())
                .userToId(reservationRequestDto.getUserToNickname())
                .categoryName(reservationRequestDto.getCategoryName())
                .color(color)
                .contents(reservationRequestDto.getContents())
                .startDate(startDate)
                .endDate(endDate)
                .phonenum(reservationRequestDto.getPhone())
                .type(type)
                .build();

    }

}
