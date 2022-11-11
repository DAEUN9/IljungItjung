package com.iljungitjung.domain.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.domain.notification.controller.NotificationController;
import com.iljungitjung.domain.notification.dto.NotificationMessageDto;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("알림 컨트롤러")
@ExtendWith(SpringExtension.class)
public class NotificationControllerTest {
    private MockMvc mockMvc;
    private NotificationController notificationController;
    @MockBean
    private NotificationService notificationService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        notificationController = new NotificationController(notificationService);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("메시지 전송")
    public void A() throws Exception {
        //given
        String message = "하이";
        String phone = "01000000000";

        NotificationMessageDto notificationMessageDto = new NotificationMessageDto(phone, message);
        List<NotificationMessageDto> messageList = new ArrayList<>();
        messageList.add(notificationMessageDto);
        String content = objectMapper.writeValueAsString(NotificationRequestDto.createFromMessages(messageList));

        when(notificationService.sendMessage(any(NotificationRequestDto.class))).thenReturn(new NotificationResponseDto("202"));
        //then
        mockMvc.perform(post("/notifications")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.statusCode").value("202"));
    }
}
