package com.piedpiper.piperchat.config;

import com.piedpiper.piperchat.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created By: Yahia
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    private AuthorizationInterceptor authorizationInterceptor;

    public MvcConfiguration(AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/registration/**");
    }
}
