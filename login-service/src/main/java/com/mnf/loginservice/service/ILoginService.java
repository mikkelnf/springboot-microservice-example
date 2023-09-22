package com.mnf.loginservice.service;

import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.loginservice.dto.LoginRequestDto;

public interface ILoginService {
    ResponseStatusOnlyDto login(LoginRequestDto requestDto);
}
