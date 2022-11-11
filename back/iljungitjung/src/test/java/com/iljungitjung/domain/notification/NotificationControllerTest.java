package com.iljungitjung.domain.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.IljungitjungApplication;
import com.iljungitjung.domain.notification.controller.NotificationController;
import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import com.iljungitjung.domain.user.entity.User;
import com.iljungitjung.domain.user.repository.UserRepository;
import com.iljungitjung.global.login.entity.RedisUser;
import com.iljungitjung.global.login.repository.RedisUserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("알림 컨트롤러")
//@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
//@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
//    private MockMvc mockMvc;
//    @InjectMocks
//    private NotificationController notificationController;
//    @MockBean
//    private NotificationService notificationService;
//    @MockBean
//    private RedisUserRepository redisUserRepository;
//    @MockBean
//    private UserRepository userRepository;
//    private ObjectMapper objectMapper;
//    private MockHttpSession session;
//
//    @BeforeEach
//    public void init() {
//        mockMvc = MockMvcBuilders.standaloneSetup(notificationController)
//                .build();
//        objectMapper = new ObjectMapper();
//        session = new MockHttpSession();
//    }
//
//    @Test
//    @DisplayName("메시지 전송")
//    public void A() throws Exception {
//        //given
//        String message = "하이";
//        String phone = "01000000000";
//        String email = "email@email";
//        String nickname = "닉넴";
//
//        User user = User.builder()
//                .email(email)
//                .nickname(nickname)
//                .build();
//        RedisUser redisUser = RedisUser.builder()
//                .id(session.getId())
//                .email(email)
//                .nickname(nickname)
//                .build();
//
//        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
//        List<NotificationMessageDto> messageList = new ArrayList<>();
//        messageList.add(notificationMessageDto);
//        String content = objectMapper.writeValueAsString(new NotificationRequestDto(messageList));
//
//        when(userRepository.save(user)).thenReturn(user);
//        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
//        when(userRepository.existsUserByEmail(email)).thenReturn(true);
//        when(redisUserRepository.findById(session.getId())).thenReturn(Optional.ofNullable(redisUser));
//        when(redisUserRepository.existsById(session.getId())).thenReturn(true);
//        when(redisUserRepository.save(redisUser)).thenReturn(redisUser);
//        when(notificationService.sendMessage(any(NotificationRequestDto.class))).thenReturn(new NotificationResponseDto(HttpStatus.ACCEPTED.toString()));
//        //then
//        mockMvc.perform(post("/notifications")
//                .content(content).session(session)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
}
