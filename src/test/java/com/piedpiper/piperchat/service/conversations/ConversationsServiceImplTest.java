package com.piedpiper.piperchat.service.conversations;

import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 1:05 PM
 **/
public class ConversationsServiceImplTest {

    private ConversationsServiceImpl conversationsService;

    @Mock
    private UserRepo userRepo;

    private Long id = 2L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        conversationsService = new ConversationsServiceImpl(userRepo);
    }

    @Test
    public void getConversations_valid_id() {
        User user = User.testUser();
        when(userRepo.findById(anyLong())).thenReturn(Optional.of(user));

        conversationsService.getUserConversations(id);

        verify(userRepo, times(1)).findById(anyLong());
        verifyNoMoreInteractions(userRepo);
    }

    @Test(expected = UserNotFoundException.class)
    public void getConversations_invalid_id() {
        when(userRepo.findById(anyLong())).thenThrow(UserNotFoundException.class);

        conversationsService.getUserConversations(id);
    }
}