package com.mnf.component.dto;

public class ResponseDto<T> extends AResponseDto {
    public T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
