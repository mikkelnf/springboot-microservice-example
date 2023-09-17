package com.mnf.loginservice.controller;

import com.mnf.common.enumeration.ResponseDtoStatusEnum;
import com.mnf.loginservice.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class BaseController<T>  extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

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
