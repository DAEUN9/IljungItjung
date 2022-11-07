package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.IljungitjungApplication;
import com.iljungitjung.domain.notification.controller.NotificationController;
import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("알림 서비스")
@SpringBootTest(classes = IljungitjungApplication.class)
@AutoConfigureMockMvc
public class NotificationControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    RedisUserRepository redisUserRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("메시지 전송")
    public void A() throws Exception {
        //given
        String email = "email";
        String nickname = "nickname";
        String profileImg = "profileImg";
        MockHttpSession session = new MockHttpSession();
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

        String message = "하이";
        String phone = "01071527518";

        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(notificationMessageDto);
        String content = objectMapper.writeValueAsString(new NotificationRequestDto(messageList));

        //then
        mockMvc.perform(post("/notifications")
                .content(content).session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
