package com.iljungitjung.global.login.interceptor;

import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginInterceptorTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RedisUserRepository redisUserRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("카카오 로그인 요청시 interceptor에 걸리지 않고 카카오 로그인 페이지로 redirect 시켜줌")
    void interceptorPathTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login/kakao"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @DisplayName("session user 없이 요청할 경우 interceptor에 걸려 401 code return")
    void interceptorTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        mockMvc.perform(MockMvcRequestBuilders.get("/users").session(session))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("session user가 존재할 경우 요청하면 정상 응답")
    void unauthorizedUserTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String email = "email";
        String nickname = "nickname";
        String profileImg = "profileImg";
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .imagePath(profileImg)
                .build();
        userRepository.save(user);

        RedisUser redisUser = RedisUser.builder()
                .id(session.getId())
                .email(email)
                .nickname(nickname)
                .profileImg(profileImg)
                .build();
        redisUserRepository.save(redisUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").session(session))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}