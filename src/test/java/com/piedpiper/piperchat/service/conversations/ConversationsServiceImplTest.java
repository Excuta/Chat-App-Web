package com.piedpiper.piperchat.service.conversations;

import com.piedpiper.piperchat.data.repo.UserRepo;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 1:05 PM
 **/
public class ConversationsServiceImplTest {

    private ConversationsServiceImpl conversationsService;

    @Mock
    private UserRepo userRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        conversationsService = new ConversationsServiceImpl(userRepo);
    }
}