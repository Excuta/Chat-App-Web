package com.piedpiper.piperchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piedpiper.piperchat.advice.GlobalAdvice;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.requestbody.CredentialsRequest;
import com.piedpiper.piperchat.exception.InvalidCredentialsException;
import com.piedpiper.piperchat.service.registration.auth.AuthService;
import com.piedpiper.piperchat.service.registration.login.LoginService;
import com.piedpiper.piperchat.service.registration.signup.SignUpService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By: Yahia
 */
public class RegistrationControllerTest {

    private MockMvc mockMvc;
    private RegistrationController registrationController;
    @Mock
    private SignUpService signUpService;
    @Mock
    private LoginService loginService;
    @Mock
    private AuthService authService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        registrationController = new RegistrationController(signUpService, loginService, authService);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
            .setControllerAdvice(GlobalAdvice.class).build();
    }

    @Test
    public void signup_valid_input() throws Exception {
        User user = User.testUser();
        user.setPassword("validPassowrd");

        mockMvc.perform(post("/registration/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(signUpService, times(1)).createUser(user);
        verifyNoMoreInteractions(signUpService);
    }

    @Test
    public void signup_invalid_input() throws Exception {
        User user = new User();

        mockMvc.perform(post("/registration/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(user))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void login_valid_input() throws Exception {
        CredentialsRequest credentialsRequest = CredentialsRequest.test();
        User user = User.testUser();
        UserAuth userAuth = UserAuth.testAuth();
        when(loginService.attemptLogin(credentialsRequest)).thenReturn(user);
        when(authService.createAuthFor(user)).thenReturn(userAuth);

        mockMvc.perform(post("/registration/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(credentialsRequest))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        verify(loginService, times(1)).attemptLogin(credentialsRequest);
        verifyNoMoreInteractions(loginService);
    }

    @Test
    public void login_invalid_input() throws Exception {
        CredentialsRequest credentialsRequest = new CredentialsRequest();

        mockMvc.perform(post("/registration/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(credentialsRequest))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void login_valid_input_user_not_exist() throws Exception {
        CredentialsRequest credentialsRequest = CredentialsRequest.test();
        when(loginService.attemptLogin(credentialsRequest)).thenThrow(new InvalidCredentialsException());

        mockMvc.perform(post("/registration/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(credentialsRequest))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }
}