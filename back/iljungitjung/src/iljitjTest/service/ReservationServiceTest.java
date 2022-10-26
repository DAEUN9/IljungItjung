import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.service.ReservationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservationServiceTest extends AbstractServiceTest{


    @Autowired
    ReservationService reservationService;

    @Autowired
    CategoryService categoryService;

    @Test
    @Order(1)
    public void 카테고리_등록() throws Exception {
        //given
        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
                "커트", "0130", "#000000");

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto);

        categoryId++;
        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }


    @Test
    @Order(2)
    public void 일정_요청() throws Exception {

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
    public void 일정_수락() throws Exception {

        //given
        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(true, "가능합니다. 연락주세요");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @Order(4)
    public void 일정_삭제() throws Exception {

        //given
        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(false, "시간이 없어요");

        //when
        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }
    @Test
    @Order(5)
    public void 카테고리_삭제() throws Exception {
        //given

        //when
        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);

        //then
        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);

    }

}
