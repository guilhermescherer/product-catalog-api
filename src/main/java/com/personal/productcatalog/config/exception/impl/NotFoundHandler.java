package com.personal.productcatalog.config.exception.impl;

import com.personal.productcatalog.config.exception.AbstractExceptionHandler;
import com.personal.productcatalog.dto.ExceptionErrorDTO;
import com.personal.productcatalog.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class NotFoundHandler extends AbstractExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NotFoundException.class})
    public ExceptionErrorDTO handle(Exception ex) {
        ExceptionErrorDTO exceptionError = getBaseException(ex);

        exceptionError.setCode(HttpStatus.NOT_FOUND.value());

        return exceptionError;
    }
}
