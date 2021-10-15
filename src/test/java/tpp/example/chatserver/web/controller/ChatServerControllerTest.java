package tpp.example.chatserver.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tpp.example.chatserver.service.ChatServerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ChatServerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatServerService chatServerService;

    @Test
    void addMessage_withAddedTypeAndBody_shouldReturnCreatedStatus() throws Exception {
        mockMvc.perform(post("/messages/send_text")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"payload\":\"test\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void addMessage_withoutContent_shouldReturnCreatedStatus() throws Exception {
        mockMvc.perform(post("/messages/send_text"))
                .andExpect(status().isBadRequest());
    }
}