package com.iljungitjung.domain.schedule.dto.reservation;

import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationBlockDto {

    @Pattern(regexp = "^[0-9]{8}$", message = "startDate는 8자리 숫자만 입력가능합니다. (ex.20221017)")
    private String date;
    @Pattern(regexp = "^[0-9]{4}$", message = "startTime은 4자리 숫자만 입력가능합니다. (ex.1500)")
    private String startTime;
    @Pattern(regexp = "^[0-9]{4}$", message = "endTime은 4자리 숫자만 입력가능합니다. (ex.1500)")
    private String endTime;

    public Schedule toEntity(Date startDate, Date endDate) {
        return Schedule.builder()
                .categoryName("block")
                .startDate(startDate)
                .endDate(endDate)
                .type(Type.BLOCK)
                .build();
    }
}
