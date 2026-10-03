package com.skedio.corestarter.advice;

import com.skedio.corestarter.template.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalRestAdviceController {
    @ExceptionHandler(ApplicationException.class)
    public ApiResponse<?> applicationException(ApplicationException applicationException) {
        return ApiResponse.ofApplicationException(applicationException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> validationExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errors = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> "%s:%s".formatted(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return ApiResponse.ofListException(errors);
    }
}
