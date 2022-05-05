package com.personal.productcatalog.config.exception.impl;

import com.personal.productcatalog.config.exception.AbstractExceptionHandler;
import com.personal.productcatalog.dto.ExceptionErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpMessageNotReadableHandler extends AbstractExceptionHandler {

    @ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ExceptionErrorDTO handle(HttpMessageNotReadableException ex){
        ExceptionErrorDTO exceptionError = getBaseException(ex);

        exceptionError.setCode(HttpStatus.PRECONDITION_FAILED.value());

        return exceptionError;
    }
}
