package com.team.piickle.common.exception;

import com.team.piickle.util.StatusCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final StatusCode errorCode;

    public GeneralException() {
        super(StatusCode.INTERNAL_ERROR.getMessage());
        this.errorCode = StatusCode.INTERNAL_ERROR;
    }

    public GeneralException(String message) {
        super(StatusCode.INTERNAL_ERROR.getMessage(message));
        this.errorCode = StatusCode.INTERNAL_ERROR;
    }

    public GeneralException(String message, Throwable cause) {
        super(StatusCode.INTERNAL_ERROR.getMessage(message), cause);
        this.errorCode = StatusCode.INTERNAL_ERROR;
    }

    public GeneralException(Throwable cause) {
        super(StatusCode.INTERNAL_ERROR.getMessage(cause));
        this.errorCode = StatusCode.INTERNAL_ERROR;
    }

    public GeneralException(StatusCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public GeneralException(StatusCode errorCode, String message) {
        super(errorCode.getMessage(message));
        this.errorCode = errorCode;
    }

    public GeneralException(StatusCode errorCode, String message, Throwable cause) {
        super(errorCode.getMessage(message), cause);
        this.errorCode = errorCode;
    }

    public GeneralException(StatusCode errorCode, Throwable cause) {
        super(errorCode.getMessage(cause), cause);
        this.errorCode = errorCode;
    }
}
