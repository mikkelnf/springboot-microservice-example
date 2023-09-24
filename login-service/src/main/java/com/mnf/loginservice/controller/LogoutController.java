package com.mnf.loginservice.controller;

import com.mnf.component.BaseController;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.loginservice.dto.LogoutRequestDto;
import com.mnf.loginservice.service.ILogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured/api/logout")
public class LogoutController extends BaseController {
    @Autowired
    ILogoutService logoutService;

    @PostMapping()
    public ResponseEntity<ResponseStatusOnlyDto> logout(@RequestBody LogoutRequestDto requestDto) {
        return createResponse(logoutService.logout(requestDto));
    }
}
