package com.piedpiper.piperchat.config;

import com.piedpiper.piperchat.bean.security.authorization.token.TokenFactory;
import com.piedpiper.piperchat.bean.security.authorization.token.contract.TokenConfig;
import com.piedpiper.piperchat.bean.security.authorization.token.contract.TokenConfigImpl;
import com.piedpiper.piperchat.bean.security.authorization.token.contract.TokenGenerator;
import com.piedpiper.piperchat.bean.security.authorization.token.contract.TokenGeneratorImpl;
import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuthFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By: Yahia
 */
@Configuration
public class AuthorizationConfig {

    @Value("${token.lifetimeinminutes}")
    private Integer tokenLifeTimeInMinutes;
    @Value("${refreshtoken.lifetimeinminutes}")
    private Integer refreshTokenLifeTimeInMinutes;

    @Bean
    public TokenConfig tokenConfig() {
        return new TokenConfigImpl(tokenLifeTimeInMinutes, refreshTokenLifeTimeInMinutes);
    }

    @Bean
    public TokenGenerator tokenGenerator() {
        return new TokenGeneratorImpl();
    }

    @Bean
    public TokenFactory tokenFactory() {
        return new TokenFactory(tokenGenerator(), tokenConfig());
    }

    @Bean
    public UserAuthFactory userAuthFactory() {
        return new UserAuthFactory(tokenFactory());
    }
}
