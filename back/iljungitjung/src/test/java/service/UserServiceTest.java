package service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.dto.SignUpUserResponseDto;
import com.iljungitjung.domain.user.dto.UserInfo;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.domain.user.service.UserService;
import com.iljungitjung.domain.user.service.UserServiceImpl;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.entity.TemporaryUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import com.iljungitjung.global.login.repository.TemporaryUserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("유저 서비스")
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;

    @MockBean
    protected HttpSession httpSession;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private TemporaryUserRepository temporaryUserRepository;
    @MockBean
    private RedisUserRepository redisUserRepository;

    @BeforeEach
    public void init(){
        userService = new UserServiceImpl(userRepository, temporaryUserRepository, redisUserRepository);
    }

    @Test
    @DisplayName("회원 가입")
    public void A() throws Exception {
        //given
        String nickname = "닉네임";
        String description = "자기소개";
        String phonenum = "01000000000";

        String id = "1";
        String email = "email@naver.com";
        String profileImg = "image_path";

        Long userId = 1L;

        SignUpDto signUpDto = new SignUpDto(nickname, description, phonenum);

        User user = signUpDto.toEntity();
        user.setId(userId);
        Optional<TemporaryUser> temporaryUser = Optional.of(new TemporaryUser(id, email, profileImg));

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        //when
        when(temporaryUserRepository.findById(mockHttpServletRequest.getSession().getId())).thenReturn(temporaryUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        SignUpUserResponseDto signUpUserResponseDto = userService.signUpUser(signUpDto, mockHttpServletRequest);

        //then
        Assertions.assertEquals(signUpUserResponseDto.getId(), 1L);

    }

    @Test
    @DisplayName("이메일로 유저 검색")
    public void B() throws Exception {

        //given
        Long userId = 1L;

        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        //when
        when(userRepository.findUserByEmail(email)).thenReturn(user);

        Optional<User> returnUser = userService.findUserByEmail(email);

        //then
        Assertions.assertEquals(returnUser.get().getId(), 1L);

    }

    @Test
    @DisplayName("이메일 중복 검사")
    public void C() throws Exception {

        //given
        Long userId = 1L;

        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        //when
        when(userRepository.existsUserByEmail(email)).thenReturn(false);

        boolean isExistUser = userService.isExistUserByEmail(email);

        //then
        Assertions.assertEquals(isExistUser, false);

    }

    @Test
    @DisplayName("닉네임으로 유저 정보 조회")
    public void D() throws Exception {

        //given
        Long userId = 1L;
        String nickname = "닉네임";

        Optional<User> user = Optional.of(User.builder().nickname(nickname).build());
        user.get().setId(userId);

        //when
        when(userRepository.findUserByNickname(nickname)).thenReturn(user);

        UserInfo userInfo = userService.getUserInfo(nickname);

        //then
        Assertions.assertEquals(userInfo.getNickname(), nickname);

    }

    @Test
    @DisplayName("세션으로 유저 정보 조회")
    public void E() throws Exception {

        //given
        Long userId = 1L;
        String nickname = "닉네임";

        Optional<User> user = Optional.of(User.builder().nickname(nickname).build());
        user.get().setId(userId);

        Optional<RedisUser> redisUser = Optional.of(RedisUser.builder().nickname(nickname).build());

        //when
        when(redisUserRepository.findById(httpSession.getId())).thenReturn(redisUser);
        when(userRepository.findUserByNickname(nickname)).thenReturn(user);

        UserInfo userInfo = userService.getUserInfo(httpSession);

        //then
        Assertions.assertEquals(userInfo.getNickname(), nickname);

    }

    @Test
    @DisplayName("세션 정보로 유저 검색")
    public void F() throws Exception {

        //given
        Long userId = 1L;
        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        Optional<RedisUser> redisUser = Optional.of(RedisUser.builder().email(email).build());

        //when
        when(redisUserRepository.findById(httpSession.getId())).thenReturn(redisUser);
        when(userRepository.findUserByEmail(email)).thenReturn(user);

        User returnUser = userService.findUserBySessionId(httpSession);

        //then
        Assertions.assertEquals(returnUser.getId(), userId);

    }

}
