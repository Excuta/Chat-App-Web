package com.piedpiper.piperchat.service.registration.signup;

import com.piedpiper.piperchat.bean.security.BCryptor;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

/**
 * Created By: Yahia
 */
@Service
public class SignUpServiceImpl implements SignUpService {
    private UserRepo userRepo;
    private BCryptor bCryptor;

    public SignUpServiceImpl(UserRepo userRepo, BCryptor bCryptor) {
        this.userRepo = userRepo;
        this.bCryptor = bCryptor;
    }

    @Override
    public void createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) throw new UserAlreadyExistsException();
        encryptPassword(user);
        userRepo.save(user);
    }

    private void encryptPassword(User user) {
        String encryptedPassword = bCryptor.encrypt(user.getPassword());
        user.setPassword(encryptedPassword);
    }
}
