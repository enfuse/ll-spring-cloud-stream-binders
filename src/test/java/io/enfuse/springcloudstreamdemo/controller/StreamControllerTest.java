package io.enfuse.springcloudstreamdemo.controller;

import io.enfuse.springcloudstreamdemo.service.DemoMessageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class StreamControllerTest {

    private static final String DATA = "TEST";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoMessageService demoMessageService;

    @Captor
    private ArgumentCaptor<Object> payloadCaptor;

    @Test
    void sendMessage_givenTextInput_sendsMessageToOutputTopic() throws Exception {
        mockMvc.perform(post("/sendMessage/" + DATA).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").value(DATA));
        verify(demoMessageService).send(payloadCaptor.capture());
        assertThat(payloadCaptor.getValue().toString()).isEqualTo(DATA);
    }
}