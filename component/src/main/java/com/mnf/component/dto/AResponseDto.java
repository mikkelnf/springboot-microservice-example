package com.mnf.component.dto;

import com.mnf.component.enumeration.ResponseDtoStatusEnum;

public abstract class AResponseDto {
    public ResponseDtoStatusEnum status;
    public String message;

    public ResponseDtoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseDtoStatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
