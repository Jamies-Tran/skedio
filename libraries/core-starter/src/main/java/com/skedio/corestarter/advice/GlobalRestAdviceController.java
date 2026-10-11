package com.skedio.corestarter.advice;

import com.skedio.corestarter.template.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalRestAdviceController {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<?>> applicationException(ApplicationException applicationException) {
        return ResponseEntity
                .status(applicationException.getStatus())
                .body(ApiResponse.ofApplicationException(applicationException));
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
