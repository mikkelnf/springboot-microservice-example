package com.mnf.user.controller;

import com.mnf.user.dto.ResponseDto;
import com.mnf.user.enumeration.ResponseDtoStatusEnum;
import com.mnf.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController<T> {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public ResponseEntity<ResponseDto<T>> createResponse(ResponseDto<T> service){
        HttpStatus status;

        if(service.getStatus() == ResponseDtoStatusEnum.SUCCESS){
            status = HttpStatus.OK;
        }else{
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<ResponseDto<T>>(service, status);
    }
}
