package com.mnf.loginservice.config;

import com.mnf.loginservice.service.ILoginService;
import com.mnf.loginservice.service.ILogoutService;
import com.mnf.loginservice.service.LoginServiceImpl;
import com.mnf.loginservice.service.LogoutServiceImplA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {
    @Bean
    public ILoginService loginService(){
        return new LoginServiceImpl();
    }

    @Bean
    public ILogoutService logoutService(){
        return new LogoutServiceImplA();
    }
}
