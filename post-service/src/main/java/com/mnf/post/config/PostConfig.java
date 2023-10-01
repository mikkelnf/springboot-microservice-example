package com.mnf.post.config;

import com.mnf.post.service.IPostService;
import com.mnf.post.service.PostServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {
    @Bean
    public IPostService postService(){
        return new PostServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
