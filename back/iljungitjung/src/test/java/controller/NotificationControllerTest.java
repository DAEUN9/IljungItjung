package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.IljungitjungApplication;
import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("알림 컨트롤러")
//@SpringBootTest(classes = IljungitjungApplication.class)
@AutoConfigureMockMvc
public class NotificationControllerTest {
//    @Autowired
//    public MockMvc mockMvc;
//    @Autowired
//    RedisUserRepository redisUserRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private MockHttpSession session;
//
//    @BeforeEach
//    public void before() {
//        String email = "email22";
//        String nickname = "nickname22";
//        String profileImg = "profileImg22";
//        User user = User.builder()
//                .email(email)
//                .nickname(nickname)
//                .imagePath(profileImg)
//                .build();
//        userRepository.save(user);
//
//        RedisUser redisUser = RedisUser.builder()
//                .id(session.getId())
//                .email(email)
//                .nickname(nickname)
//                .profileImg(profileImg)
//                .build();
//        redisUserRepository.save(redisUser);
//    }
//
//    @Test
//    @DisplayName("메시지 전송")
//    public void A() throws Exception {
//        //given
//        String message = "하이";
//        String phone = "01071527518";
//
//        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
//        List<NotificationMessageDto> messageList = new ArrayList<>();
//        messageList.add(notificationMessageDto);
//        String content = objectMapper.writeValueAsString(new NotificationRequestDto(messageList));
//
//        //then
//        mockMvc.perform(post("/notifications")
//                .content(content).session(session)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}