package com.iljungitjung.domain.schedule.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
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
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("스케줄 서비스")
@ExtendWith(SpringExtension.class)
class ScheduleServiceTest{

    private ScheduleService scheduleService;
    @MockBean
    private HttpSession httpSession;
    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;


    @BeforeEach
    void init(){
        scheduleService = new ScheduleServiceImpl(scheduleRepository, userRepository, userService);
    }

    @Test
    @DisplayName("타인의 일정 리스트 조회(startDate, endDate 입력)")
    void viewScheduleByOthersGivenDate(){

        //given
        User userFrom = createUserFrom();
        Optional<User> userTo = Optional.of(createUserToWithCategoryList());

        List<Schedule> scheduleList = new ArrayList<>();

        Schedule schedule = createSchedule();
        schedule.accpeted();
        scheduleList.add(schedule);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDate = sdf.format(date);
        String endDate = sdf.format(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(cal.getTime()).endDate(cal.getTime()).type(Type.REQUEST).build();
        schedule.setId(2L);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(userTo.get().getNickname(), startDate, endDate, httpSession);

        //then
        Assertions.assertEquals(1L, scheduleViewResponseDto.getAcceptList().get(0).getId());
    }
    @Test
    @DisplayName("타인의 일정 리스트 조회(startDate, endDate 미입력)")
    void viewScheduleByOthers() {

        //given
        User userFrom = createUserFrom();
        Optional<User> userTo = Optional.of(createUserToWithCategoryList());

        List<Schedule> scheduleList = new ArrayList<>();

        Schedule schedule = createSchedule();
        schedule.accpeted();
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(userTo.get().getNickname(), null, null, httpSession);

        //then
        Assertions.assertEquals(1L, scheduleViewResponseDto.getAcceptList().get(0).getId());
    }
    @Test
    @DisplayName("일정 리스트 조회시 닉네임에 해당하는 유저의 일정이 없음")
    void noExistScheduleByNicknameWhenViewSchedule(){

        //given
        User userFrom = createUserFrom();
        String errorNickname = "2";

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);

        //then
        Assertions.assertThrows(NoExistUserException.class, () -> {
            scheduleService.scheduleView(errorNickname, null, null, httpSession);
        });
    }

    @Test
    @DisplayName("일정 리스트 조회시 일정의 날짜 형식을 잘못 입력함")
    void inputErrorDateWhenViewScheduleList(){
        //given
        String errorDate="error";

        User userFrom = createUserFrom();
        Optional<User> userTo = Optional.of(createUserToWithCategoryList());

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);

        //then
        Assertions.assertThrows(DateFormatErrorException.class, () -> {
            scheduleService.scheduleView(userTo.get().getNickname(), errorDate, errorDate, httpSession);
        });
    }


    @Test
    @DisplayName("본인의 일정 리스트 조회(startDate, endDate 입력)")
    void viewScheduleListByMeGivenDate() {

        //given
        User userFrom = createUserFrom();
        Optional<User> userTo = Optional.of(createUserToWithCategoryList());

        List<Schedule> scheduleList = new ArrayList<>();

        Schedule schedule = createSchedule();
        scheduleList.add(schedule);

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDate = sdf.format(date);
        String endDate = sdf.format(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(cal.getTime()).endDate(cal.getTime()).type(Type.REQUEST).build();
        schedule.setId(2L);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(Optional.of(userFrom));
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(userFrom.getNickname(), startDate, endDate, httpSession);

        //then
        Assertions.assertEquals(1L, scheduleViewResponseDto.getRequestList().get(0).getId());

    }

    @Test
    @DisplayName("본인의 일정 리스트 조회(startDate, endDate 미입력)")
    void viewScheduleListByMe() {

        //given
        User userFrom = createUserFrom();
        Optional<User> userTo = Optional.of(createUserToWithCategoryList());

        List<Schedule> scheduleList = new ArrayList<>();

        Schedule schedule = createSchedule();
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(new Date()).endDate(new Date()).type(Type.CANCEL).build();
        schedule.setId(2L);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(new Date()).endDate(new Date()).type(Type.BLOCK).build();
        schedule.setId(3L);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(new Date()).endDate(new Date()).type(Type.ACCEPT).build();
        schedule.setId(4L);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(userTo.get().getNickname(), null, null, httpSession);

        //then
        Assertions.assertEquals(4L, scheduleViewResponseDto.getAcceptList().get(0).getId());

    }

    @Test
    @DisplayName("일정 상세 조회")
    void viewScheduleDetail(){

        //given


        Optional<Schedule> schedule = Optional.of(createSchedule());

        //when
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(1L);

        //then
        Assertions.assertEquals(1L, scheduleViewDetailResponseDto.getId());
    }

    @Test
    @DisplayName("id에 해당하는 일정이 없음")
    void NoExistScheduleByIdWhenViewScheduleDetail(){

        //given
        Long scheduleId = 1L;

        //when

        //then
        Assertions.assertThrows(NoExistScheduleDetailException.class, () -> {
            scheduleService.scheduleViewDetail(scheduleId);
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

        return category;
    }
    private User createUserFrom(){
        Long userFromId = 1L;
        String nickname="1";

        User userFrom = User.builder().nickname(nickname).build();
        userFrom.setId(userFromId);

        return userFrom;
    }

    private User createUserToWithCategoryList(){
        Long userToId = 2L;
        String nickname="2";

        User userTo = User.builder().nickname(nickname).build();
        userTo.setId(userToId);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(createCategory());
        userTo.setCategoryList(categoryList);

        return userTo;
    }
}
