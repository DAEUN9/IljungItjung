package service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import org.junit.jupiter.api.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class B_ReservationServiceTest extends AbstractServiceTest{


    @Test
    @Order(1)
    public void 카테고리_등록_서비스() throws Exception {
        //given
        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                "커트", "0130", "#000000");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        //then
        categoryId++;
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }


    @Test
    @Order(2)
    public void 일정_요청_서비스() throws Exception {

        //given
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto("1", "2", "20221017", "1500", "안녕하세요", "01011111111", "커트");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(3)
    public void 일정_수락_서비스() throws Exception {

        //given
        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(true, "가능합니다. 연락주세요");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(4)
    public void 일정_삭제_서비스() throws Exception {

        //given
        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(false, "시간이 없어요");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(5)
    public void 일정_차단_요청_서비스() throws Exception {

        //given
        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto("1", "공휴일", "공휴일이라서 쉽니다.", "20221017", "1500", "1630");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto);

        //then
        scheduleId++;
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(6)
    public void 카테고리_삭제_서비스() throws Exception {
        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }

}
