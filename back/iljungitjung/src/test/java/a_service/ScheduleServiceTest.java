package a_service;

import com.iljungitjung.domain.category.dto.CategoryCreateRequestDto;
import com.iljungitjung.domain.category.dto.CategoryIdResponseDto;
import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.category.service.CategoryService;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationIdResponseDto;
import com.iljungitjung.domain.schedule.dto.reservation.ReservationRequestDto;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.schedule.service.ReservationService;
import com.iljungitjung.domain.schedule.service.ReservationServiceImpl;
import com.iljungitjung.domain.schedule.service.ScheduleService;
import com.iljungitjung.domain.schedule.service.ScheduleServiceImpl;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("스케줄 서비스")
public class ScheduleServiceTest extends AbstractServiceTest {

    private ScheduleService scheduleService;

    private ReservationService reservationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private HttpSession session;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private RedisUserRepository redisUserRepository;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void init(){
        scheduleService = new ScheduleServiceImpl(scheduleRepository, categoryRepository, userRepository);
        reservationService = new ReservationServiceImpl(scheduleRepository, categoryRepository, userRepository,userService);
    }

//    @Test
//    @DisplayName("카테고리 등록")
//    public void A() throws Exception {
//        //given
//        String categoryName = "커트3";
//        String time = "0130";
//        String color = "#000000";
//
//        CategoryCreateRequestDto categoryCreateRequestDto = new CategoryCreateRequestDto(
//                categoryName, time, color);
//        CategoryIdResponseDto resultCategoryIdResponseDto = new CategoryIdResponseDto(1L);
//
//        //when
//        when(categoryService.addCategory(categoryCreateRequestDto, session)).thenReturn(resultCategoryIdResponseDto);
//
//        CategoryIdResponseDto categoryIdResponseDto = categoryService.addCategory(categoryCreateRequestDto, session);
//
//        //then
//        Assertions.assertEquals(categoryIdResponseDto.getId(), 1L);
//    }

    @Test
    @DisplayName("일정 요청")
    public void B() throws Exception {

        //given
        String userToNickname = "2";
        String date = "20221017";
        String startTime = "1500";
        String contents = "안녕하세요";
        String phone = "01011111111";
        String categoryName = "커트";
        String userEmail = "eamil@naver.com";

        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(userToNickname, date, startTime, contents, phone, categoryName);
        User user = User.builder()
                        .nickname("1")
                .email(userEmail)
                .build();
        Optional<User> user2 = Optional.ofNullable(User.builder()
                .nickname("2")
                .build());
        Optional<Category> category = Optional.of(new Category(categoryName, "#000130", "0130"));

        Schedule schedule = reservationRequestDto.toScheduleEntity(reservationRequestDto, new Date(), new Date(), category.get().getColor(), Type.REQUEST);
        schedule.setId(1L);

        ReservationIdResponseDto result = new ReservationIdResponseDto(1L);

//        ReservationIdResponseDto reservationIdResponseDto = new ReservationIdResponseDto(1L);


        //when
        when(userService.findUserBySessionId(session)).thenReturn(user);
        when(categoryRepository.findByCategoryNameAndUser_Email(categoryName, userEmail)).thenReturn(category);
        when(userRepository.findUserByNickname(userToNickname)).thenReturn(user2);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationRequest(reservationRequestDto, session);

        //then
        Assertions.assertEquals(reservationIdResponseDto.getId(), 1L);

    }
//
//    @Test
//    @DisplayName("일정 차단")
//    public void C() throws Exception {
//
//        //given
//        String userFromNickname = "1";
//        String title = "공휴일";
//        String contents = "공휴일이라서 쉽니다.";
//        String date = "20221017";
//        String startTime = "1500";
//        String endTime = "1630";
//
//        ReservationBlockRequestDto reservationBlockRequestDto = new ReservationBlockRequestDto(
//                userFromNickname, title, contents, date, startTime, endTime);
//
//        //when
//        ReservationIdResponseDto reservationIdResponseDto = reservationService.reservationBlock(reservationBlockRequestDto);
//
//        //then
//        scheduleId++;
//        Assertions.assertEquals(reservationIdResponseDto.getId(), scheduleId);
//
//    }
//
//    @Test
//    @DisplayName("일정 리스트 조회")
//    public void D() throws Exception {
//
//        //given
//        String nickname = "1";
//        String startDate = "20221017";
//        String endDate = "20221018";
//        boolean isMyView = false;
//
//        //when
//        ScheduleViewResponseDto scheduleViewResponseDto = scheduleService.scheduleView(nickname, startDate, endDate, isMyView);
//
//        //then
//        int sum = scheduleViewResponseDto.getRequestList().size()+scheduleViewResponseDto.getBlockList().size()+scheduleViewResponseDto.getAcceptList().size()+scheduleViewResponseDto.getCancelList().size();
//
//        Assertions.assertEquals(sum, scheduleId);
//
//
//    }
//    @Test
//    @DisplayName("일정 상세 조회")
//    public void E() throws Exception {
//        //given
//
//        //when
//        ScheduleViewDetailResponseDto scheduleViewDetailResponseDto = scheduleService.scheduleViewDetail(scheduleId);
//
//        //then
//        Assertions.assertEquals(scheduleViewDetailResponseDto.getId(), scheduleId);
//    }
//    @Test
//    @DisplayName("카테고리 삭제")
//    public void F() throws Exception {
//        //given
//
//        //when
//        CategoryIdResponseDto categoryIdResponseDto = categoryService.deleteCategory(categoryId);
//
//        //then
//        Assertions.assertEquals(categoryIdResponseDto.getId(), categoryId);
//
//    }
}
