package com.piedpiper.piperchat.service.registration.signup;

import com.piedpiper.piperchat.bean.security.BCryptor;
import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Created By: Yahia
 */
@RunWith(JUnit4.class)
public class SignUpServiceImplTest {

    private SignUpServiceImpl signUpService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private BCryptor bCryptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        signUpService = new SignUpServiceImpl(userRepo, bCryptor);
    }

    @Test
    public void createUser_valid_user() {
        User user = new User();
        when(userRepo.existsByEmail(anyString())).thenReturn(false);

        signUpService.createUser(user);

        verify(userRepo, times(1)).existsByEmail(any());
        verify(bCryptor, times(1)).encrypt(any());
        verify(userRepo, times(1)).save(user);
        verifyNoMoreInteractions(userRepo, bCryptor);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void createUser_user_exists() {
        User user = new User();
        when(userRepo.existsByEmail(any())).thenReturn(true);

        signUpService.createUser(user);
    }
}