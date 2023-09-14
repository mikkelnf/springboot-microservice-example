package com.mnf.user.dto;

import com.mnf.user.enumeration.ResponseDtoStatusEnum;

public class ResponseDto<T> {
    public ResponseDtoStatusEnum status;

    public String message;

    public T content;

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
