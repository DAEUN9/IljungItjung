import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.category.repository.CategoryRepository;
import com.iljungitjung.domain.schedule.entity.Schedule;
import com.iljungitjung.domain.schedule.entity.Type;
import com.iljungitjung.domain.schedule.exception.DateFormatErrorException;
import com.iljungitjung.domain.schedule.repository.ScheduleRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class C_ScheduleRepositoryTest  extends AbstractTest{

    @Autowired
    ScheduleRepository scheduleRepository;

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

        //given
        Schedule schedule = new Schedule("1", "2", startDate, endDate, "커트", "#000000", "간단한 커트", "01011111111", Type.REQUEST);

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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        Date startDate;
        Date endDate;
        try{
            startDate = formatter.parse("202210270000");
            endDate = formatter.parse("202210272359");
        }catch (Exception e){
            throw new DateFormatErrorException();
        }
        //given

        //when
        List<Schedule> scheduleList = scheduleRepository.findScheduleByUserFromIdAndStartDateBetween("1", startDate, endDate);

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
