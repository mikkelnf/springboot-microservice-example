package com.mnf.loginservice.controller;

import com.mnf.component.ABaseController;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.loginservice.dto.LoginRequestDto;
import com.mnf.loginservice.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secured/api/login")
public class LoginController extends ABaseController {
    @Autowired
    ILoginService loginService;

    @PostMapping()
    public ResponseEntity<ResponseStatusOnlyDto> login(@RequestBody LoginRequestDto requestDto) {
        return createResponse(loginService.login(requestDto));
    }
}
