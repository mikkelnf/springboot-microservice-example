package com.mnf.loginservice.controller;

import com.mnf.loginservice.dto.LoginRequestDto;
import com.mnf.loginservice.dto.ResponseDto;
import com.mnf.loginservice.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Autowired
    ILoginService loginService;

    @PostMapping()
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        return createResponse(loginService.login(requestDto));
    }

}
