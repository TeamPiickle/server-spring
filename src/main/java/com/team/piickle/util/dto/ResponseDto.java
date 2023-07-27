package com.team.piickle.util.dto;

import com.team.piickle.util.StatusCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto {

    private final Boolean success;
    private final Integer status;
    private final String message;

    public static ResponseDto of(Boolean success, StatusCode code) {
        return new ResponseDto(success, code.getCode(), code.getMessage());
    }

    public static ResponseDto of(Boolean success, StatusCode errorCode, Exception e) {
        return new ResponseDto(success, errorCode.getCode(), e.getMessage());
    }

    public static ResponseDto of(Boolean success, StatusCode errorCode, String message) {
        return new ResponseDto(success, errorCode.getCode(), message);
    }
}
