package com.piedpiper.piperchat.service.registration.signup;

import com.piedpiper.piperchat.data.model.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserAlreadyExistsException;
import org.mindrot.jbcrypt.BCrypt;
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
        encryptPassword(user);
        userRepo.save(user);
    }

    private void encryptPassword(User user) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(hashedPassword);
    }
}
