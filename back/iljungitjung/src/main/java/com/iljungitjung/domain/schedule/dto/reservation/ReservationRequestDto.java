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

    @Pattern(regexp = "^[a-z|A-Z|0-9|ㄱ-ㅎ|가-힣]{2,10}$", message = "닉네임은 최소 2자, 최대 10자 영어, 한글, 숫자만 입력가능합니다.")
    private String userToNickname;

    @Pattern(regexp = "^[0-9]{8}$", message = "date는 숫자만 입력가능합니다.(ex.20221017)")
    private String date;

    @Pattern(regexp = "^[0-9]{4}$", message = "startTime은 숫자만 입력가능합니다.(ex.1500)")
    private String startTime;

    @Size(min = 0, max = 100, message = "contents는 최소 0자, 최대 100자만 가능합니다.")
    private String contents;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9]?)?([0-9]{7,8})$", message = "전화번호는 10~11자리의 숫자만 입력가능합니다.")
    private String phone;

    private String categoryName;

    public Schedule toEntity(Date startDate, Date endDate, String color) {
        return Schedule.builder()
                .categoryName(this.categoryName)
                .color(color)
                .contents(this.contents)
                .startDate(startDate)
                .endDate(endDate)
                .phonenum(this.phone)
                .type(Type.REQUEST)
                .build();

    }

}
