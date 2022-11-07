package com.iljungitjung.domain.schedule;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.exception.NoGrantDeleteCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.reservation.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ReservationServiceImpl;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("상담예약 서비스 에러")
@ExtendWith(SpringExtension.class)
public class ReservationServiceErrorTest {

    private ReservationService reservationService;
    @MockBean
    protected HttpSession httpSession;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    public void init(){
        reservationService = new ReservationServiceImpl(scheduleRepository, categoryRepository, userRepository, userService);
    }

    @Test
    @DisplayName("일정 예약시 선택한 카테고리가 존재하지 않음")
    void noExistCategoryWhenRequestSchedule(){
        //given
        User user = User.builder().build();

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);


        //then
        Assertions.assertThrows(NoExistCategoryException.class, () -> {
            reservationService.reservationRequest(reservationRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 예약시 날짜 형식 입력을 잘못함")
    void inputErrorDateWhenRequestSchedule(){
        //given
        User user = User.builder().build();
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        Optional<Category> category = Optional.of(new Category());

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);
        when(categoryRepository.findByCategoryNameAndUser_Email(any(), any())).thenReturn(category);

        //then
        Assertions.assertThrows(DateFormatErrorException.class, () -> {
            reservationService.reservationRequest(reservationRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 예약시 일정 주인이 존재하지 않음")
    void noExistUserWhenRequestSchedule(){
        //given
        User user = User.builder().build();

        String categoryName = "커트";
        String color = "#000000";
        String time = "0130";
        Optional<Category> category = Optional.of(new Category(categoryName, color, time));

        String userToNickname = "2";
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userToNickname, date, startTime, contents, phone, categoryName);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(user);
        when(categoryRepository.findByCategoryNameAndUser_Email(any(), any())).thenReturn(category);

        //then
        Assertions.assertThrows(NoExistUserException.class, () -> {
            reservationService.reservationRequest(reservationRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 관리시 해당 일정이 존재하지 않음")
    void noExistScheduleWhenManageSchedule(){
        //given
        User user = User.builder().build();

        //when
        when(userService.findUserBySessionId(any())).thenReturn(user);
        //then
    }

    @Test
    @DisplayName("일정 관리시 일정 주인이 아닌 신청자가 해당 일정을 수락함")
    void noGrantAcceptScheduleWhenManageSchedule(){
        //given
        //when
        //then
    }

    @Test
    @DisplayName("일정 관리시 일정 주인, 신청자가 아닌 제3자가 접근함")
    void noGrantAccessScheduleWhenManageSchedule(){
        //given
        //when
        //then
    }

    @Test
    @DisplayName("일정 차단시 날짜 형식 입력을 잘못함")
    void inputDateErrorWhenBlockSchedule(){
        //given
        //when
        //then
    }

    @Test
    @DisplayName("예약 리스트 조회시 날짜 형식 입력을 잘못함")
    void inputDateErrorWhenViewReservation(){
        //given
        //when
        //then
    }

}
