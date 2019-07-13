package com.piedpiper.piperchat.service.registration.signup;

import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

/**
 * Created By: Yahia
 */
@Service
public class SignUpServiceImpl implements SignUpService {
    private UserRepo userRepo;

    public SignUpServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) throw new UserAlreadyExistsException();
        userRepo.save(user);
    }
}
