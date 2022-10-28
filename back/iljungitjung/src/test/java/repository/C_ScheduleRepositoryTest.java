package repository;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import com.iljungitjung.domain.user.entity.Users;
import com.iljungitjung.domain.user.exception.NoExistUserException;
import com.iljungitjung.domain.user.repository.UserRepository;
import main.AbstractTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class C_ScheduleRepositoryTest  extends AbstractRepositoryTest {

    @Test
    @Order(1)
    public void 일정_등록_레포지토리() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        Date endDate;
        try{
            startDate = formatter.parse("202210271500");
            endDate = formatter.parse("202210271600");
        }catch (Exception e){
            throw new DateFormatErrorException();
        }
        Users user1 = userRepository.findUsersByNickname("1").get();
        Users user2 = userRepository.findUsersByNickname("2").get();
        //given
        Schedule schedule = new Schedule(startDate, endDate, "커트", "#000000", "간단한 커트", "01011111111", Type.REQUEST);

        schedule.setScheduleRequestList(user1);
        schedule.setScheduleResponseList(user2);

        //when
        Schedule savedSchedule = scheduleRepository.save(schedule);

        //then
        Assertions.assertEquals(schedule.getCategoryName(), savedSchedule.getCategoryName());
        Assertions.assertEquals(schedule.getColor(), savedSchedule.getColor());
        Assertions.assertEquals(schedule.getContents(), savedSchedule.getContents());
        Assertions.assertEquals(schedule.getStartDate(), savedSchedule.getStartDate());
    }

    @Test
    @Order(2)
    public void 일정_리스트조회_기간설정_레포지토리() throws Exception {

        //given

        //when
        Users user = userRepository.findUsersByNickname("1").orElseThrow(() -> {
            throw new NoExistUserException();
        });
        List<Schedule> scheduleList = user.getScheduleRequestList();

        //then
        Assertions.assertEquals(1, scheduleList.size());
    }

    @Test
    @Order(3)
    public void 일정_상세조회_레포지토리() throws Exception {


        //given

        //when
        Optional<Schedule> schedule = scheduleRepository.findScheduleById(1L);

        //then
        Assertions.assertEquals("커트", schedule.get().getCategoryName());
    }


}
