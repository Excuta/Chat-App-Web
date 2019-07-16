package com.piedpiper.piperchat.service.conversations;

import com.piedpiper.piperchat.data.model.conversation.Conversation;
import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ConversationsServiceImpl implements ConversationsService {
    private UserRepo userRepo;

    public ConversationsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Collection<Conversation> getUserConversations(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (!userOptional.isPresent()) throw new UserNotFoundException();
        return userOptional.get().getConversations();
    }
}
