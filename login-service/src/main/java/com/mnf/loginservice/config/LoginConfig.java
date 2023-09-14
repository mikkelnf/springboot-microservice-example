package com.mnf.loginservice.config;

import com.mnf.loginservice.service.ILoginService;
import com.mnf.loginservice.service.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {
    @Bean
    public ILoginService loginService(){
        return new LoginServiceImpl();
    }
}
