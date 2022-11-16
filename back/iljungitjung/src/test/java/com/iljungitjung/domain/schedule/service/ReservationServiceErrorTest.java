package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.exception.NoExistCategoryException;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationBlockListRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationManageRequestDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.*;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
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
    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    public void init(){
        reservationService = new ReservationServiceImpl(scheduleRepository, categoryRepository, userRepository, userService, notificationService);
    }

    @Test
    @DisplayName("일정 예약시 선택한 카테고리가 존재하지 않음")
    void noExistCategoryWhenRequestSchedule(){
        //given
        User userFrom = createUserFrom();
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";

        Optional<User> userTo = Optional.of(createUserToWithCategoryList());
        Category category = createCategory();

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userTo.get().getNickname(), date, startTime, contents, phone, category.getCategoryName());


        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(Optional.of(userFrom));

        //then
        Assertions.assertThrows(NoExistCategoryException.class, () -> {
            reservationService.reservationRequest(reservationRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 예약시 날짜 형식 입력을 잘못함")
    void inputErrorDateWhenRequestSchedule(){
        //given
        User userFrom = createUserFrom();
        String date = "error";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";

        Optional<User> userTo = Optional.of(createUserToWithCategoryList());
        Category category = createCategory();

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userTo.get().getNickname(), date, startTime, contents, phone, category.getCategoryName());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(categoryRepository.findByCategoryNameAndUser_Email(any(String.class), any(String.class))).thenReturn(Optional.of(category));

        //then
        Assertions.assertThrows(DateFormatErrorException.class, () -> {
            reservationService.reservationRequest(reservationRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 예약시 일정 주인이 존재하지 않음")
    void noExistUserWhenRequestSchedule(){
        //given
        User userFrom = createUserFrom();
        String userToNickname = "2";

        Optional<Category> category = Optional.of(createCategory());

        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userToNickname, date, startTime, contents, phone, category.get().getCategoryName());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.findByCategoryNameAndUser_Email(any(String.class), any(String.class))).thenReturn(category);

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
        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto();

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(user);

        //then
        Assertions.assertThrows(NoExistScheduleDetailException.class, () -> {
            reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 관리시 일정 주인이 아닌 신청자가 해당 일정을 수락함")
    void noGrantAcceptScheduleWhenManageSchedule(){
        //given;
        User userFrom = createUserFrom();

        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);
        Optional<Schedule> schedule = Optional.of(createSchedule());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        //then
        Assertions.assertThrows(NoGrantAcceptScheduleException.class, () -> {
            reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);
        });
    }

    @Test
    @DisplayName("일정 관리시 일정 주인, 신청자가 아닌 제3자가 접근함")
    void noGrantAccessScheduleWhenManageSchedule(){
        //given
        User userError = createUserFrom();
        userError.setId(3L);

        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);
        Optional<Schedule> schedule = Optional.of(createSchedule());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userError);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        //then
        Assertions.assertThrows(NoGrantAccessScheduleException.class, () -> {
            reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);
        });
    }

//    @Test
//    @DisplayName("일정 차단시 날짜 형식 입력을 잘못함")
//    void inputDateErrorWhenBlockSchedule(){
//        //given
//        User userFrom = User.builder().build();
//        ReservationBlockListRequestDto reservationBlockListRequestDto = new ReservationBlockListRequestDto();
//
//        //when
//        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
//
//        //then
//        Assertions.assertThrows(DateFormatErrorException.class, () -> {
//            reservationService.reservationBlock(reservationBlockListRequestDto, httpSession);
//        });
//    }

    @Test
    @DisplayName("예약 리스트 조회시 날짜 형식 입력을 잘못함")
    void inputDateErrorWhenViewReservation(){
        //given
        User userFrom = createUserFrom();
        String errorStartDate = "error";
        String errorEndDate = "error";

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);

        //then
        Assertions.assertThrows(DateFormatErrorException.class, () -> {
            reservationService.reservationView(errorStartDate, errorEndDate, httpSession);
        });
    }
    @Test
    @DisplayName("일정 삭제시 권한 없음")
    void NoGrantDeleteWhenDeleteSchedule(){
        //given
        User userFrom = createUserFrom();

        String reason = "이유";

        Optional<Schedule> schedule = Optional.of(createSchedule());


        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        //then
        Assertions.assertThrows(NoGrantDeleteScheduleException.class, () -> {
            reservationService.reservationDelete(1L, reason, httpSession);
        });
    }
    private Schedule createSchedule() {
        Long scheduleId = 1L;
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        Date endTime = new Date();
        Date startTime = new Date();
        String contents = "contents";
        Type type = Type.REQUEST;
        String phoneNum = "01044444444";

        Schedule schedule = Schedule.builder()
                .userFrom(createUserFrom())
                .userTo(createUserToWithCategoryList())
                .categoryName(categoryName)
                .color(categoryColor)
                .contents(contents)
                .type(type)
                .phonenum(phoneNum)
                .endDate(endTime)
                .startDate(startTime)
                .build();
        schedule.setId(scheduleId);

        return schedule;
    }
    private Category createCategory(){
        Long categoryId = 1L;
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        String time = "0130";

        Category category = Category.builder()
                .categoryName(categoryName)
                .color(categoryColor)
                .time(time)
                .build();
        category.setId(categoryId);

        return category;
    }
    private User createUserFrom(){
        Long userFromId = 1L;
        String nickname="1";
        String email = "userFrom@naver.com";

        User userFrom = User.builder().nickname(nickname).email(email).build();
        userFrom.setId(userFromId);

        return userFrom;
    }

    private User createUserToWithCategoryList(){
        Long userToId = 2L;
        String nickname="2";
        String email = "userTo@naver.com";

        User userTo = User.builder().nickname(nickname).email(email).build();
        userTo.setId(userToId);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(createCategory());
        userTo.setCategoryList(categoryList);

        return userTo;
    }
}
