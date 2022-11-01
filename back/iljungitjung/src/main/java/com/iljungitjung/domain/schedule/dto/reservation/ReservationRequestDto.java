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
public class ReservationRequestDto {

    private String userFromNickname;

    @NotBlank(message = "userToNickname은 비워둘 수 없습니다.")
    private String userToNickname;

    @NotBlank(message = "date는 비워둘 수 없습니다.")
    @Size(min=8, max=8)
    private String date;

    @NotBlank(message = "startTime은 비워둘 수 없습니다.")
    @Size(min=4, max=4)
    private String startTime;

    private String contents;
    private String phone;

    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
    private String categoryName;

    public Schedule toScheduleEntity(ReservationRequestDto reservationRequestDto, Date startDate, Date endDate, String color, Type type) {
        return Schedule.builder()
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
