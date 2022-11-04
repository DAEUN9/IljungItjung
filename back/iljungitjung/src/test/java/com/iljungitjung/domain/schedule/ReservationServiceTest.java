package com.iljungitjung.domain.schedule;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.dto.reservation.*;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ReservationServiceImpl;
import com.iljungitjung.domain.user.entity.User;
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

    @BeforeEach
    public void init(){
        reservationService = new ReservationServiceImpl(scheduleRepository, categoryRepository, userRepository, userService);
    }

    @Test
    @DisplayName("일정 요청")
    public void A() throws Exception {

        //given
        String categoryName = "커트";
        String color = "#000000";
        String time = "0130";

        Long scheduleId=1L;
        String userFromNickname = "1";
        String userToNickname = "2";
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";
        String email = "email@naver.com";

        User userFrom = User.builder().nickname(userFromNickname).email(email).build();
        Optional<User> userTo = Optional.of(User.builder().nickname(userToNickname).build());
        Optional<Category> category = Optional.of(new Category(categoryName, color, time));

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userToNickname, date, startTime, contents, phone, categoryName);
        Schedule schedule = reservationRequestDto.toScheduleEntity(reservationRequestDto, new Date(), new Date(), category.get().getColor(), Type.REQUEST);
        schedule.setId(scheduleId);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userFrom);
        when(categoryRepository.findByCategoryNameAndUser_Email(categoryName, email)).thenReturn(category);
        when(userRepository.findUserByNickname(userToNickname)).thenReturn(userTo);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), 1L);


    }

    @Test
    @DisplayName("일정 주인이 일정 수락")
    public void B() throws Exception {

        //given
        boolean accept = true;
        String reason = "가능합니다. 잘부탁드려요";

        Long scheduleId=1L;

        Long userToId = 2L;
        User userTo = User.builder().build();
        userTo.setId(userToId);

        Optional<Schedule> schedule = Optional.of(Schedule.builder().userTo(userTo).build());
        schedule.get().setId(scheduleId);

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(scheduleId)).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);
    }
    @Test
    @DisplayName("일정 주인이 일정 취소")
    public void C() throws Exception {

        //given
        boolean accept = false;
        String reason = "가능합니다. 잘부탁드려요";

        Long scheduleId=1L;

        Long userToId = 2L;
        User userTo = User.builder().build();
        userTo.setId(userToId);

        Optional<Schedule> schedule = Optional.of(Schedule.builder().userTo(userTo).build());
        schedule.get().setId(scheduleId);

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(scheduleId)).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);
    }

    @Test
    @DisplayName("일정 신청자가 일정 취소")
    public void D() throws Exception {
        //given
        boolean accept = false;
        String reason = "가능합니다. 잘부탁드려요";

        Long scheduleId=1L;

        Long userToId = 1L;
        User userTo = User.builder().build();
        userTo.setId(userToId);

        Long userFromId = 2L;
        User userFrom = User.builder().build();
        userFrom.setId(userFromId);

        Optional<Schedule> schedule = Optional.of(Schedule.builder().userTo(userTo).userFrom(userFrom).build());
        schedule.get().setId(scheduleId);

        ReservationManageRequestDto reservationManageRequestDto = new ReservationManageRequestDto(accept, reason);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userFrom);
        when(scheduleRepository.findScheduleById(scheduleId)).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationManage(scheduleId, reservationManageRequestDto, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);
    }
    @Test
    @DisplayName("일정 신청자가 예약 리스트 조회")
    public void E() throws Exception {

        //given
        Long userFromId = 1L;
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

        User userFrom = User.builder().build();
        userFrom.setId(userFromId);
        Schedule schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.REQUEST).build();
        schedule.setId(scheduleId);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId+1);
        scheduleList.add(schedule);

        schedule = Schedule.builder().userFrom(userFrom).startDate(startDateFormat).endDate(endDateFormat).type(Type.CANCEL).build();
        schedule.setId(scheduleId+2);
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userFrom);
        when(scheduleRepository.findByUserFrom_IdIs(userFrom.getId())).thenReturn(scheduleList);

        ReservationViewResponseDto reservationViewResponseDto = reservationService.reservationView(date, date, httpSession);

        //then
        Assertions.assertEquals(reservationViewResponseDto.getRequestList().get(0).getId(), 1L);
    }


    @Test
    @DisplayName("일정 주인이 일정 삭제")
    public void F() throws Exception {

        //given
        String reason = "가능합니다. 잘부탁드려요";

        Long userToId = 2L;
        User userTo = User.builder().build();
        userTo.setId(userToId);

        Long scheduleId=1L;
        Optional<Schedule> schedule = Optional.of(Schedule.builder().userTo(userTo).build());
        schedule.get().setId(scheduleId);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userTo);
        when(scheduleRepository.findScheduleById(scheduleId)).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationDelete(scheduleId, reason, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);

    }

    @Test
    @DisplayName("일정 차단")
    public void G() throws Exception {

        //given
        Long userToId = 2L;
        User userTo = User.builder().build();
        userTo.setId(userToId);

        Long scheduleId = 1L;
        String title = "공휴일";
        String contents = "공휴일이라서 쉽니다.";
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


        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto(title, contents, date, startTime, endTime);

        Schedule schedule = reservationBlockRequestDto.toScheduleEntity(reservationBlockRequestDto, startDateFormat, endDateFormat);
        schedule.setId(scheduleId);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userTo);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto, httpSession);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), 1L);

    }

}
