package com.iljungitjung.domain.schedule;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.exception.NoExistScheduleDetailException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.domain.schedule.service.ScheduleServiceImpl;
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

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

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
    void viewScheduleByOthersGivenDate() throws Exception {

        //given
        Long scheduleId = 1L;
        String date="20221017";
        String startTime = "1500";
        String endTime = "1630";
        Date startDateFormat;
        Date endDateFormat;

        String categoryName = "커트";
        String color = "#000000";
        String time = "0130";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDateFormat = formatter.parse(date+startTime);
            endDateFormat = formatter.parse(date+endTime);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        Long userFromId=1L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=2L;
        String nickname="2";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(categoryName, color, time));
        userTo.get().setCategoryList(categoryList);

        List<Schedule> scheduleList = new ArrayList<>();

        Schedule schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).build();
        schedule.setId(scheduleId);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).build();
        schedule.setId(scheduleId+2);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).build();
        schedule.setId(scheduleId+3);
        scheduleList.add(schedule);

        date="20221020";

        try{
            startDateFormat = formatter.parse(date+startTime);
            endDateFormat = formatter.parse(date+endTime);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.REQUEST).build();
        schedule.setId(scheduleId+4);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getRequestList().get(0).getId(), 5L);
    }
    @Test
    @DisplayName("타인의 일정 리스트 조회(startDate, endDate 미입력)")
    void viewScheduleByOthers() throws Exception {

        //given
        Long scheduleId = 1L;
        String date="20221017";
        String startTime = "1500";
        String endTime = "1630";
        Date startDateFormat;
        Date endDateFormat;

        String categoryName = "커트";
        String color = "#000000";
        String time = "0130";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDateFormat = formatter.parse(date+startTime);
            endDateFormat = formatter.parse(date+endTime);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        Long userFromId=1L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=2L;
        String nickname="2";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(categoryName, color, time));
        userTo.get().setCategoryList(categoryList);

        List<Schedule> scheduleList = new ArrayList<>();
        Schedule schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.REQUEST).build();
        schedule.setId(scheduleId);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.CANCEL).build();
        schedule.setId(scheduleId+2);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(new Date()).endDate(new Date()).type(Type.CANCEL).build();
        schedule.setId(scheduleId+3);
        scheduleList.add(schedule);

        date=null;

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getRequestList().get(0).getId(), 1L);
    }
    @Test
    @DisplayName("일정 리스트 조회시 닉네임에 해당하는 유저의 일정이 없음")
    void noExistScheduleByNicknameWhenViewSchedule(){

        //given
        Long userFromId=1L;
        String nickname = "1";
        String date="20221017";
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);

        //then
        Assertions.assertThrows(NoExistUserException.class, () -> {
            scheduleService.scheduleView(nickname, date, date, httpSession);
        });
    }

    @Test
    @DisplayName("일정 리스트 조회시 일정의 날짜 형식을 잘못 입력함")
    void inputErrorDateWhenViewScheduleList(){
        //given
        Long userFromId=1L;
        String date="error";
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=2L;
        String nickname="2";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);

        //then
        Assertions.assertThrows(DateFormatErrorException.class, () -> {
            scheduleService.scheduleView(nickname, date, date, httpSession);
        });
    }


    @Test
    @DisplayName("본인의 일정 리스트 조회(startDate, endDate 입력)")
    void viewScheduleListByMeGivenDate() {

        //given
        Long scheduleId = 1L;
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

        Long userFromId=1L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=1L;
        String nickname="1";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);



        List<Schedule> scheduleList = new ArrayList<>();
        Schedule schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.BLOCK).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(new Date()).endDate(new Date()).type(Type.BLOCK).build();
        schedule.setId(scheduleId+2);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getAcceptList().get(0).getId(), 1L);

    }

    @Test
    @DisplayName("본인의 일정 리스트 조회(startDate, endDate 미입력)")
    void viewScheduleListByMe() {

        //given
        Long scheduleId = 1L;
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

        Long userFromId=1L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=1L;
        String nickname="1";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        Schedule schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.BLOCK).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        date=null;

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getAcceptList().get(0).getId(), 1L);

    }

    @Test
    @DisplayName("일정 상세 조회")
    void viewScheduleDetail() throws Exception {

        //given
        Long scheduleId = 1L;
        String categoryName = "커트";
        String contents = "안녕하세요";
        String phone = "01011111111";
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

        Optional<Schedule> schedule = Optional.of(Schedule.builder().categoryName(categoryName).contents(contents).startDate(startDateFormat).endDate(endDateFormat).phonenum(phone).build());
        schedule.get().setId(scheduleId);

        //when
        when(scheduleRepository.findScheduleById(any(Long.class))).thenReturn(schedule);

        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(scheduleId);

        //then
        Assertions.assertEquals(scheduleViewDetailResponseDto.getId(), scheduleId);
    }

    @Test
    @DisplayName("조회한 일정이 기간 내의 일정이 아님")
    void notValidScheduleWhenViewScheduleDetail(){
        //given
        Long scheduleId = 1L;
        String date="20221017";
        String startTime = "1500";
        String endTime = "1630";
        Date startDateFormat;
        Date endDateFormat;

        String categoryName = "커트";
        String color = "#000000";
        String time = "0130";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        try{
            startDateFormat = formatter.parse(date+startTime);
            endDateFormat = formatter.parse(date+endTime);
        }catch (Exception e){
            throw new DateFormatErrorException();
        }

        Long userFromId=1L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Long userToId=2L;
        String nickname="2";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(categoryName, color, time));
        userTo.get().setCategoryList(categoryList);

        Schedule schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.BLOCK).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.REQUEST).build();
        schedule.setId(scheduleId+2);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.CANCEL).build();
        schedule.setId(scheduleId+3);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(any(HttpSession.class))).thenReturn(userFrom);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(any(Long.class))).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getAcceptList().get(0).getId(), 1L);
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
        String categoryName = "categoryName";
        String categoryColor = "#000000";
        Date endTime = new Date();
        Date startTime = new Date();
        String contents = "contents";
        Type type = Type.REQUEST;
        String phoneNum = "01044444444";
        return Schedule.builder()
                .categoryName(categoryName)
                .color(categoryColor)
                .contents(contents)
                .type(type)
                .phonenum(phoneNum)
                .endDate(endTime)
                .startDate(startTime)
                .build();
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

        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        return userFrom;
    }

    private User createUserTo(){
        Long userToId = 2L;

        User userTo = User.builder().build();
        userTo.setId(userToId);

        return userTo;
    }
}
