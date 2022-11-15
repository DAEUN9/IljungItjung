package com.iljungitjung.domain.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iljungitjung.domain.notification.controller.NotificationController;
import com.iljungitjung.domain.notification.dto.NotificationMessage;
import com.iljungitjung.domain.notification.dto.NotificationRequestDto;
import com.iljungitjung.domain.notification.dto.NotificationResponseDto;
import com.iljungitjung.domain.notification.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
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
        String content = "하이";
        String phone = "01000000000";

        NotificationMessage notificationMessage = new NotificationMessage(phone, content);
        List<NotificationMessage> messageList = makeMessages(notificationMessage);
        String contents = objectMapper.writeValueAsString(NotificationRequestDto.createFromMessages(messageList));

        when(notificationService.sendMessage(any(NotificationRequestDto.class))).thenReturn(new NotificationResponseDto(statusAccepted()));
        //then
        mockMvc.perform(post("/notifications")
                .content(contents)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.statusCode").value(statusAccepted()));
    }

    private List<NotificationMessage> makeMessages(NotificationMessage... message){
        return Arrays.asList(message);
    }
    private String statusAccepted() {
        return HttpStatus.ACCEPTED.value()+"";
    }
}
