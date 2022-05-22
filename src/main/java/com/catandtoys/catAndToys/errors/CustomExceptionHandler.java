package com.catandtoys.catAndToys.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SystemException.class)
    public ErrorMessageInfo SystemException(SystemException e) {
        return new ErrorMessageInfo(e.getMessage());
    }
}
