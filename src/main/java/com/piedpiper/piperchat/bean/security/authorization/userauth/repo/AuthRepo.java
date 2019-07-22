package com.piedpiper.piperchat.bean.security.authorization.userauth.repo;

import com.piedpiper.piperchat.bean.security.authorization.token.Token;
import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created By: Yahia
 */
@Repository
public interface AuthRepo extends CrudRepository<UserAuth, Long> {

    UserAuth findByTokenOrRefreshToken(Token token, Token refresh);

    UserAuth findByUser(User user);
}
