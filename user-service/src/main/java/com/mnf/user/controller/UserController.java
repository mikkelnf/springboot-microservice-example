package com.mnf.user.controller;

import com.mnf.component.BaseController;
import com.mnf.component.dto.ResponseDto;
import com.mnf.component.dto.ResponseStatusOnlyDto;
import com.mnf.user.dto.AddUserRequestDto;
import com.mnf.user.dto.UserResponseDto;
import com.mnf.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secured/api/user")
public class UserController extends BaseController {
    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getById(@PathVariable String id){
        return createResponse(userService.findOneById(id));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<ResponseStatusOnlyDto> addUser(AddUserRequestDto requestDto){
        return createResponse(userService.addUser(requestDto));
    }
}
