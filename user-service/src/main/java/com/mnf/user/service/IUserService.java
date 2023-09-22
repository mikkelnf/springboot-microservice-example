package com.mnf.user.service;

import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.user.dto.AddUserRequestDto;
import com.mnf.user.dto.UserResponseDto;

public interface IUserService {
    ResponseDto<UserResponseDto> findOneById(String id);
    ResponseDto<ResponseStatusOnlyDto> addUser(AddUserRequestDto requestDto);
}
