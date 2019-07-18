package com.piedpiper.piperchat.config;

import com.piedpiper.piperchat.bean.validation.IdParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: Yahia.Ragae
 * Creation Date: 7/16/2019 2:21 PM
 **/
@Configuration
public class GlobalConfig {
    @Bean
    public IdParser idParser() {
        return new IdParser();
    }
}
