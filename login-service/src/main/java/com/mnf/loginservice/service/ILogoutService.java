package com.mnf.loginservice.service;

import com.mnf.compos.dto.ResponseStatusOnlyDto;
import com.mnf.loginservice.dto.LogoutRequestDto;

public interface ILogoutService {
    ResponseStatusOnlyDto logout(LogoutRequestDto id);
}
