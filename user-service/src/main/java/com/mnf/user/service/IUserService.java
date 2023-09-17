package com.mnf.user.service;

import com.mnf.common.entity.UserEntity;
import com.mnf.user.dto.AddUserRequestDto;
import com.mnf.user.dto.ResponseDto;
import com.mnf.user.dto.UserResponseDto;

public interface IUserService {
    ResponseDto<UserResponseDto> findOneById(String id);
    ResponseDto<UserEntity> addUser(AddUserRequestDto requestDto);
}
