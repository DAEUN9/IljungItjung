package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.user.entity.Users;
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

    public Schedule toScheduleEntity(ReservationRequestDto reservationRequestDto, Users userFrom, Users userTo, Date startDate, Date endDate, String color, Type type) {
        return Schedule.builder()
                .userFrom(userFrom)
                .userTo(userTo)
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
