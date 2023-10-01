package com.mnf.component.dto;

import com.mnf.component.enumeration.ResponseDtoStatusEnum;

public class ResponseStatusOnlyDto extends AResponseDto{
    public ResponseStatusOnlyDto() {
    }
    public ResponseStatusOnlyDto(ResponseDtoStatusEnum status) {
        super.setStatus(status);
    }
    public ResponseStatusOnlyDto(ResponseDtoStatusEnum status, String message) {
        super.setStatus(status);
        super.setMessage(message);
    }
}
