package com.iljungitjung.domain.user.service;

import com.iljungitjung.domain.category.entity.Category;
import com.iljungitjung.domain.user.dto.SignUpDto;
import com.iljungitjung.domain.user.dto.SignUpUserResponseDto;
import com.iljungitjung.domain.user.dto.UserInfo;
import com.iljungitjung.domain.user.dto.UserInfoList;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void SighUp() {
        //given
        String nickname = "닉네임";
        String introduction = "자기소개";

        String id = "1";
        String email = "email@naver.com";
        String profileImg = "profile_img";

        Long userId = 1L;

        SignUpDto signUpDto = new SignUpDto(nickname, introduction);

        User user = signUpDto.toEntity();
        user.setId(userId);
        Optional<TemporaryUser> temporaryUser = Optional.of(new TemporaryUser(id, email, profileImg));

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        //when
        when(temporaryUserRepository.findById(any(String.class))).thenReturn(temporaryUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        SignUpUserResponseDto signUpUserResponseDto = userService.signUpUser(signUpDto, mockHttpServletRequest);

        //then
        assertEquals(1L, signUpUserResponseDto.getId());
    }

    @Test
    @DisplayName("이메일로 유저 검색")
    public void findUserByEmail() {

        //given
        Long userId = 1L;

        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        //when
        when(userRepository.findUserByEmail(any(String.class))).thenReturn(user);

        Optional<User> returnUser = userService.findUserByEmail(email);

        //then
        assertEquals(1L, returnUser.get().getId());

    }

    @Test
    @DisplayName("이메일 중복 검사(성공)")
    public void emailRedundancyCheck() {

        //given
        Long userId = 1L;

        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        //when
        when(userRepository.existsUserByEmail(any(String.class))).thenReturn(false);

        boolean isExistUser = userService.isExistUserByEmail(email);

        //then
        assertEquals(false, isExistUser);

    }

    @Test
    @DisplayName("닉네임으로 유저 정보 조회")
    public void findUserInfoByNickname() {

        //given
        Long userId = 1L;
        String nickname = "닉네임";

        Optional<User> user = Optional.of(User.builder().nickname(nickname).build());
        user.get().setId(userId);

        //when
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(user);

        UserInfo userInfo = userService.getUserInfo(nickname);

        //then
        assertEquals(nickname, userInfo.getNickname());

    }

    @Test
    @DisplayName("세션으로 유저 정보 조회")
    public void findUserInfoBySession(){

        //given
        Long userId = 1L;
        String nickname = "닉네임";

        Optional<User> user = Optional.of(User.builder().nickname(nickname).build());
        user.get().setId(userId);

        Optional<RedisUser> redisUser = Optional.of(RedisUser.builder().nickname(nickname).build());

        //when
        when(redisUserRepository.findById(any())).thenReturn(redisUser);
        when(userRepository.findUserByNickname(any(String.class))).thenReturn(user);

        UserInfo userInfo = userService.getUserInfo(httpSession);

        //then
        assertEquals(nickname, userInfo.getNickname());

    }

    @Test
    @DisplayName("세션 정보로 유저 검색")
    public void findUserBySession()  {

        //given
        Long userId = 1L;
        String email = "email@naver.com";

        Optional<User> user = Optional.of(User.builder().build());
        user.get().setId(userId);

        Optional<RedisUser> redisUser = Optional.of(RedisUser.builder().email(email).build());

        //when
        when(redisUserRepository.findById(any())).thenReturn(redisUser);
        when(userRepository.findUserByEmail(any(String.class))).thenReturn(user);

        User returnUser = userService.findUserBySessionId(httpSession);

        //then
        assertEquals(1L, returnUser.getId());

    }

    @Test
    @DisplayName("닉네임이 포함된 유저 리스트 검색")
    void searchUsersByNickname(){
        String searchNickname = "aaa";

        String firstUserNickname = "aaacaa";
        User firstUser = User.builder()
                .nickname(firstUserNickname)
                .build();

        String secondUserNickname = "aaaba";
        User secondUser = User.builder()
                .nickname(secondUserNickname)
                .build();

        String thirdUserNickname = "aaabaaa";
        User thirdUser = User.builder()
                .nickname(thirdUserNickname)
                .build();

        List<User> userList = Arrays.asList(firstUser, secondUser, thirdUser);

        when(userRepository.findByNicknameContaining(searchNickname)).thenReturn(userList);
        when(userRepository.findUserByNickname(firstUserNickname)).thenReturn(Optional.ofNullable(firstUser));
        when(userRepository.findUserByNickname(secondUserNickname)).thenReturn(Optional.ofNullable(secondUser));
        when(userRepository.findUserByNickname(thirdUserNickname)).thenReturn(Optional.ofNullable(thirdUser));

        UserInfoList result = userService.getUserInfoList(searchNickname);

        assertEquals(result.getUsers().size(), 3);
        assertEquals(result.getUsers().get(0).getNickname(), firstUserNickname);
        assertEquals(result.getUsers().get(1).getNickname(), secondUserNickname);
        assertEquals(result.getUsers().get(2).getNickname(), thirdUserNickname);
    }
}
