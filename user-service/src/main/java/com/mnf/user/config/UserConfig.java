package com.mnf.user.config;

import com.mnf.user.controller.UserController;
import com.mnf.user.service.IUserService;
import com.mnf.user.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public IUserService userService(){
        return new UserServiceImpl();
    }
}
