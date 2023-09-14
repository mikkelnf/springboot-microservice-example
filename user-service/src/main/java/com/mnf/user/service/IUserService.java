package com.mnf.user.service;

import com.mnf.user.dto.AddUserRequestDto;
import com.mnf.user.dto.ResponseDto;
import com.mnf.user.dto.UserResponseDto;
import com.mnf.user.entity.UserEntity;

public interface IUserService {
    ResponseDto<UserResponseDto> findOneById(String id);
    ResponseDto<UserEntity> addUser(AddUserRequestDto requestDto);
}
