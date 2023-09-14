package com.mnf.user.controller;

import com.mnf.user.dto.ResponseDto;
import com.mnf.user.enumeration.ResponseDtoStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController<T> {
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
