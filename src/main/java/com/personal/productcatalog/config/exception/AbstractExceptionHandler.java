package com.personal.productcatalog.config.exception;

import com.personal.productcatalog.dto.ErrorDTO;
import com.personal.productcatalog.dto.ExceptionErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    protected ExceptionErrorDTO getBaseException(Exception ex) {
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());

        List<String> trace = getTraceByLimit(ex);
        exceptionError.setTrace(trace);

        List<ErrorDTO> errors = getErrors(ex);
        exceptionError.setErrors(errors);

        return exceptionError;
    }

    private List<ErrorDTO> getErrors(Exception ex) {
        List<ErrorDTO> errors = new ArrayList<>();

        if(ex instanceof BindException) {
            List<FieldError> fieldErrors = ((BindException) ex).getBindingResult().getFieldErrors();

            fieldErrors.forEach(e -> {
                String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
                errors.add(new ErrorDTO(e.getField(), message));
            });
        }

        return errors;
    }

    private List<String> getTraceByLimit(Exception ex) {
        return Arrays.stream(ex.getStackTrace())
                .limit(5)
                .map(StackTraceElement::toString)
                .collect(Collectors.toList());
    }
}
