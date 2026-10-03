package com.skedio.corestarter.advice;

import com.skedio.corestarter.template.ApplicationErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {
    ApplicationErrorCode errorCode;
    String message;
    HttpStatus status;

    public ApplicationException(ApplicationErrorCode errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public ApplicationException(ApplicationErrorCode errorCode, HttpStatus status) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
        this.status = status;
    }
}
