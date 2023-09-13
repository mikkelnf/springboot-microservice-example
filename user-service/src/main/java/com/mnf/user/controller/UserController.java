package com.mnf.user.controller;

import com.mnf.user.dto.ResponseDto;
import com.mnf.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable String id){
        return new ResponseEntity<ResponseDto>(userService.findOneById(id),HttpStatus.OK);
    }
}
