package a_service;

import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewDetailResponseDto;
import com.iljungitjung.domain.schedule.dto.schedule.ScheduleViewResponseDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.domain.schedule.service.ScheduleServiceImpl;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("스케줄 서비스")
public class ScheduleServiceTest extends AbstractServiceTest {

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
    public void init(){
        scheduleService = new ScheduleServiceImpl(scheduleRepository, userRepository, userService);
    }

    @Test
    @DisplayName("타인의 일정 리스트 조회")
    public void A() throws Exception {

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

        Long userToId=2L;
        String nickname="2";
        Optional<User> userTo = Optional.of(User.builder().nickname(nickname).build());
        userTo.get().setId(userToId);

        Schedule schedule = Schedule.builder().userFrom(userFrom).userTo(userTo.get()).startDate(startDateFormat).endDate(endDateFormat).type(Type.ACCEPT).build();
        schedule.setId(scheduleId);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule);

        //when
        when(userService.findUserBySessionId(httpSession)).thenReturn(userFrom);
        when(userRepository.findUserByNickname(nickname)).thenReturn(userTo);
        when(scheduleRepository.findByUserTo_IdIs(userTo.get().getId())).thenReturn(scheduleList);

        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, date, date, httpSession);

        //then
        Assertions.assertEquals(scheduleViewResponseDto.getAcceptList().get(0).getId(), 1L);


    }
    @Test
    @DisplayName("일정 상세 조회")
    public void B() throws Exception {

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
        when(scheduleRepository.findScheduleById(scheduleId)).thenReturn(schedule);

        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(scheduleId);

        //then
        Assertions.assertEquals(scheduleViewDetailResponseDto.getId(), scheduleId);
    }
}
