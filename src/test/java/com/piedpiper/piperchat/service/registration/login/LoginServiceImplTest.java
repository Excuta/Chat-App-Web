package com.piedpiper.piperchat.service.registration.login;

import com.piedpiper.piperchat.bean.security.cryptography.BCryptor;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.data.requestbody.CredentialsRequest;
import com.piedpiper.piperchat.exception.InvalidCredentialsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created By: Yahia
 */
public class LoginServiceImplTest {
    private LoginServiceImpl loginService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private BCryptor bCryptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginService = new LoginServiceImpl(userRepo, bCryptor);
    }

    @Test
    public void attemptLogin_valid_input() {
        CredentialsRequest credentialsRequest = CredentialsRequest.test();
        User user = new User();
        when(userRepo.findFirstByEmail(any())).thenReturn(Optional.of(user));
        when(bCryptor.matches(any(), any())).thenReturn(true);

        loginService.attemptLogin(credentialsRequest);

        verify(userRepo, times(1)).findFirstByEmail(any());
        verify(bCryptor, times(1)).matches(any(), any());
        verify(userRepo, times(1)).save(any());
        verifyNoMoreInteractions(userRepo, bCryptor);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void attemptLogin_user_doesnt_exist() {
        CredentialsRequest credentialsRequest = CredentialsRequest.test();
        User user = new User();
        when(userRepo.findFirstByEmail(any())).thenReturn(Optional.empty());

        loginService.attemptLogin(credentialsRequest);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void attemptLogin_wrong_password() {
        CredentialsRequest credentialsRequest = CredentialsRequest.test();
        User user = new User();
        when(userRepo.findFirstByEmail(any())).thenReturn(Optional.of(user));
        when(bCryptor.matches(any(), any())).thenReturn(false);

        loginService.attemptLogin(credentialsRequest);
    }
}