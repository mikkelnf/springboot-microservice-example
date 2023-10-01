package com.mnf.post.service;

import com.mnf.component.dto.GetPaginationRequestDto;
import com.mnf.component.dto.GetPaginationResponseDto;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.post.dto.UserRequestDto;
import com.mnf.post.dto.GetUserResponseDto;

public interface IUserService {
    ResponseStatusOnlyDto add(UserRequestDto requestDto);
    ResponseDto<GetUserResponseDto> getById(String id);
    ResponseDto<GetPaginationResponseDto<GetUserResponseDto>> getPagination(GetPaginationRequestDto<UserRequestDto> requestDto);
    ResponseStatusOnlyDto update(UserRequestDto requestDto);
    ResponseStatusOnlyDto delete(UserRequestDto requestDto);
}
