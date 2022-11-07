package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {

    @NotBlank(message = "userToNickname은 비워둘 수 없습니다.")
    private String userToNickname;

    @Size(min=8, max=8, message = "형식을 맞춰주세요 (ex.20221017)")
    private String date;

    @Size(min=4, max=4, message = "형식을 맞춰주세요 (ex.1500)")
    private String startTime;

    private String contents;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)?([0-9]{7,8})$", message = "전화번호는 10~11자리의 숫자만 입력가능합니다.")
    private String phone;

    @NotBlank(message = "categoryName은 비워둘 수 없습니다.")
    private String categoryName;

    public Schedule toEntity(Date startDate, Date endDate, String color, Type type) {
        return Schedule.builder()
                .categoryName(this.categoryName)
                .color(color)
                .contents(this.contents)
                .startDate(startDate)
                .endDate(endDate)
                .phonenum(this.phone)
                .type(type)
                .build();

    }

}
