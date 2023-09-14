package com.mnf.loginservice.service;

import com.mnf.loginservice.dto.LoginRequestDto;
import com.mnf.loginservice.dto.ResponseDto;

public interface ILoginService {
    ResponseDto login(LoginRequestDto requestDto);
}
