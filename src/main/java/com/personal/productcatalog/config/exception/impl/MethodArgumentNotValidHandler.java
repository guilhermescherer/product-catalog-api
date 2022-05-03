package com.personal.productcatalog.config.exception.impl;

import com.personal.productcatalog.config.exception.AbstractExceptionHandler;
import com.personal.productcatalog.dto.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidHandler extends AbstractExceptionHandler {

    @ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionErrorDTO handle(MethodArgumentNotValidException ex){
        ExceptionErrorDTO exceptionError = getBaseException(ex);

        exceptionError.setCode(HttpStatus.PRECONDITION_FAILED.value());

        return exceptionError;
    }
}
