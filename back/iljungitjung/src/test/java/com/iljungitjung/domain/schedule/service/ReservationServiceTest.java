package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.schedule.dto.reservation.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("상담예약 서비스")
@ExtendWith(SpringExtension.class)
public class ReservationServiceTest{

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
        reservationService = new ReservationServiceImpl(scheduleRepository, categoryRepository, userRepository, userService);
    }

    @Test
    @DisplayName("일정 예약 요청")
    public void requestSchedule() {

        //given
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";

        User userFrom = createUserFrom();

        Optional<User> userTo = Optional.of(createUserToWithCategoryList());
        Optional<Category> category = Optional.of(createCategory());

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userTo.get().getNickname(), date, startTime, contents, phone, category.get().getCategoryName());
        Schedule schedule = createSchedule();

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(categoryRepository.findByCategoryNameAndUser_Email(any(String.class), any(String.class))).thenReturn(category);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, reservationIdResponseDto.getId());


    }

    @Test
    @DisplayName("일정 주인이 일정 수락")
    public void acceptScheduleFromOwner(){

        //given
        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";

        User userTo = createUserToWithCategoryList();

        Optional<Schedule> schedule = Optional.of(createSchedule());

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, reservationIdResponseDto.getId());
    }
    @Test
    @DisplayName("일정 주인이 일정 취소")
    public void cancelScheduleFromOwner(){

        //given
        boolean accept = false;
        String reason = "죄송합니다.";

        User userTo = createUserToWithCategoryList();

        Optional<Schedule> schedule = Optional.of(createSchedule());

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, reservationIdResponseDto.getId());
    }

    @Test
    @DisplayName("일정 신청자가 일정 취소")
    public void cancelScheduleFromApplicant(){
        //given
        boolean accept = false;
        String reason = "죄송합니다.";

        User userFrom = createUserFrom();

        Optional<Schedule> schedule = Optional.of(createSchedule());

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(1L, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, reservationIdResponseDto.getId());
    }
    @Test
    @DisplayName("일정 신청자가 예약 리스트 조회")
    public void viewScheduleFromApplicant() {

        //given
        User userFrom = createUserFrom();

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDate = sdf.format(date);
        String endDate = sdf.format(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);

        List<Schedule> scheduleList = new ArrayList<>();
        Schedule schedule = Schedule.builder().userFrom(userFrom).startDate(date).endDate(date).type(Type.REQUEST).build();
        schedule.setId(1L);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(date).endDate(date).type(Type.ACCEPT).build();
        schedule.setId(2L);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(date).endDate(date).type(Type.CANCEL).build();
        schedule.setId(3L);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(cal.getTime()).endDate(cal.getTime()).type(Type.CANCEL).build();
        schedule.setId(4L);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(scheduleRepository.findByUserFrom_IdIs(any(Long.class))).thenReturn(scheduleList);

        ReservationViewResponseDto reservationViewResponseDto = reservationService.reservationView(startDate, endDate, httpSession);

        //then
        Assertions.assertEquals(1L, reservationViewResponseDto.getRequestList().get(0).getId());
    }


    @Test
    @DisplayName("일정 주인이 일정 삭제")
    public void deleteScheduleFromOwner() {

        //given
        String reason = "가능합니다. 잘부탁드려요";

        User userTo = createUserToWithCategoryList();

        Optional<Schedule> schedule = Optional.of(createSchedule());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        reservationService.reservationDelete(1L, reason, httpSession);

        //then

    }

    @Test
    @DisplayName("일정 차단")
    public void blockSchedule() {

        //given
        User userTo = createUserToWithCategoryList();

        Long scheduleId = 1L;
        boolean block = false;
        String date="20221017";
        String startTime = "1500";
        String endTime = "1630";
        Date startDateFormat;
        Date endDateFormat;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDateFormat = formatter.parse(date+startTime);
            endDateFormat = formatter.parse(date+endTime);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto(block, date, startTime, endTime);

        Schedule schedule = reservationBlockRequestDto.toEntity(startDateFormat, endDateFormat);
        schedule.setId(scheduleId);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userTo);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto, httpSession);

        //then
        Assertions.assertEquals(1L, reservationIdResponseDto.getId());

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
