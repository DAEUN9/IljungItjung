package repository;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class C_UserRepositoryTest extends AbstractRepositoryTest {


    @Test
    @Order(1)
    public void 유저_저장_레포지토리() throws Exception {

        //given
        Users user = new Users("3", "01011111111", "asd", "qwe", "1500", "2100");

        //when

        userRepository.save(user);
        Users searchUser = userRepository.findUsersByNickname("3").get();
        //then
        Assertions.assertEquals(searchUser.getPhonenum(), user.getPhonenum());
        Assertions.assertEquals(searchUser.getNickname(), user.getNickname());
    }

    @Test
    @Order(2)
    public void 유저_닉네임_검색_레포지토리() throws Exception {

        //given
        Users user = new Users("1", "01011111111", "asd", "qwe", "1500", "2100");

        Users searchUser = userRepository.findUsersByNickname("1").get();
        //when

        //then
        Assertions.assertEquals(searchUser.getPhonenum(), user.getPhonenum());
        Assertions.assertEquals(searchUser.getNickname(), user.getNickname());
    }


}
