package com.piedpiper.piperchat.config;

import com.piedpiper.piperchat.bean.security.cryptography.BCryptor;
import com.piedpiper.piperchat.bean.security.cryptography.BCryptorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created By: Yahia
 */
@Configuration
public class RegistrationConfiguration {

    @Bean
    public BCryptor bCryptor() {
        return new BCryptorImpl();
    }
}
