package com.mnf.post.service;

import com.mnf.compos.dto.*;
import com.mnf.post.dto.PostRequestDto;
import com.mnf.post.dto.PostResponseDto;

public interface IPostService {
    ResponseStatusOnlyDto add(PostRequestDto requestDto);
    ResponseDto<PostResponseDto> getOneById(String id);
    ResponseDto<PostResponseDto> getOneBySlug(String slug);
    ResponseDto<GetPaginationResponseDto<PostResponseDto>> getPagination(GetPaginationRequestDto<PostRequestDto> requestDto);
    ResponseStatusOnlyDto update(PostRequestDto requestDto);
    ResponseStatusOnlyDto delete(PostRequestDto requestDto);
}
