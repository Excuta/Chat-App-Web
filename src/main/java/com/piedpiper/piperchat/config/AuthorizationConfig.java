package com.piedpiper.piperchat.config;

import com.piedpiper.piperchat.data.model.authorization.token.TokenFactory;
import com.piedpiper.piperchat.bean.security.authorization.TokenConfig;
import com.piedpiper.piperchat.bean.security.authorization.TokenConfigImpl;
import com.piedpiper.piperchat.bean.security.authorization.TokenGenerator;
import com.piedpiper.piperchat.bean.security.authorization.TokenGeneratorImpl;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuthFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By: Yahia
 */
@Configuration
public class AuthorizationConfig {

    @Value("${token.lifetimeinseconds}")
    private Integer tokenLifeTimeInSeconds;
    @Value("${refreshtoken.lifetimeinseconds}")
    private Integer refreshTokenLifeTimeInSeconds;

    @Bean
    public TokenConfig tokenConfig() {
        return new TokenConfigImpl(tokenLifeTimeInSeconds, refreshTokenLifeTimeInSeconds);
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
