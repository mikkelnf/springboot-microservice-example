package com.mnf.user.service;

import com.mnf.user.dto.ResponseDto;
import com.mnf.user.entity.UserEntity;
import org.springframework.stereotype.Service;

public interface IUserService {
    ResponseDto<UserEntity> findOneById(String id);
}
