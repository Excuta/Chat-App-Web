package com.piedpiper.piperchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piedpiper.piperchat.advice.GlobalAdvice;
import com.piedpiper.piperchat.data.model.Conversation;
import com.piedpiper.piperchat.exception.UserNotFoundException;
import com.piedpiper.piperchat.service.conversations.ConversationsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Creadted by: Yahia
 **/
public class ConversationsControllerTest {

    private ConversationsController conversationsController;
    @Mock
    private ConversationsService conversationsService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        conversationsController = new ConversationsController(conversationsService);
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(conversationsController)
            .setControllerAdvice(GlobalAdvice.class).build();
    }

    @Test
    public void getConversations_valid_input() throws Exception {
        String userId = "23";
        Collection<Conversation> conversations = new ArrayList<>();
        when(conversationsService.getUserConversations(anyLong())).thenReturn(conversations);

        mockMvc.perform(get("/conversations/" + userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userId))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(conversationsService, times(1)).getUserConversations(anyLong());
        verifyNoMoreInteractions(conversationsService);
    }

    @Test
    public void getConversations_invalid_input() throws Exception {
        String userId = "as";

        mockMvc.perform(get("/conversations/" + userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userId))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void login_valid_input_user_not_exist() throws Exception {
        String userId = "23";
        when(conversationsService.getUserConversations(anyLong())).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/conversations/" + userId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(userId))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}