package com.piedpiper.piperchat.service.registration.login;

import com.piedpiper.piperchat.bean.security.cryptography.BCryptor;
import com.piedpiper.piperchat.data.model.user.User;
import com.piedpiper.piperchat.data.repo.UserRepo;
import com.piedpiper.piperchat.data.requestbody.CredentialsRequest;
import com.piedpiper.piperchat.exception.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created By: Yahia
 */
@Service
public class LoginServiceImpl implements LoginService {
    private UserRepo userRepo;
    private BCryptor bCryptor;

    public LoginServiceImpl(UserRepo userRepo, BCryptor bCryptor) {
        this.userRepo = userRepo;
        this.bCryptor = bCryptor;
    }

    @Override
    public User attemptLogin(CredentialsRequest credentialsRequest) {
        Optional<User> userOptional = userRepo.findFirstByEmail(credentialsRequest.getEmail());
        if (!userOptional.isPresent()) throw new InvalidCredentialsException();
        User user = userOptional.get();
        if (!bCryptor.matches(credentialsRequest.getPassword(), user.getPassword())) throw new InvalidCredentialsException();
        user.setLoggedIn(true);
        userRepo.save(user);
        return user;
    }
}
