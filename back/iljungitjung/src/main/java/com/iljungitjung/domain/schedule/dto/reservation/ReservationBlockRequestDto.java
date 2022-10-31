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

    @NotBlank(message = "date는 비워둘 수 없습니다.")
    @Size(min=8, max=8)
    private String date;

    @NotBlank(message = "date는 비워둘 수 없습니다.")
    @Size(min=4, max=4)
    private String startTime;

    @NotBlank(message = "date는 비워둘 수 없습니다.")
    @Size(min=4, max=4)
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
