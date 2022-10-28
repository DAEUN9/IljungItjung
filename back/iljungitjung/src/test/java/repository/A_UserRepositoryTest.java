package repository;

import com.iljungitjung.domain.user.entity.Users;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class A_UserRepositoryTest extends AbstractRepositoryTest {


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
