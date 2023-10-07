package com.mnf.post.service;

import com.mnf.compos.dto.GetPaginationRequestDto;
import com.mnf.compos.dto.GetPaginationResponseDto;
import com.mnf.compos.dto.ResponseDto;
import com.mnf.compos.dto.ResponseStatusOnlyDto;
import com.mnf.post.dto.UserRequestDto;
import com.mnf.post.dto.GetUserResponseDto;

public interface IUserService {
    ResponseStatusOnlyDto add(UserRequestDto requestDto);
    ResponseDto<GetUserResponseDto> getById(String id);
    ResponseDto<GetPaginationResponseDto<GetUserResponseDto>> getPagination(GetPaginationRequestDto<UserRequestDto> requestDto);
    ResponseStatusOnlyDto update(UserRequestDto requestDto);
    ResponseStatusOnlyDto delete(UserRequestDto requestDto);
}
