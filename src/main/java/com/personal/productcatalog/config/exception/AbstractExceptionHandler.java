package com.personal.productcatalog.config.exception;

import com.personal.productcatalog.dto.ErrorDTO;
import com.personal.productcatalog.dto.ExceptionErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import static org.apache.commons.lang3.ArrayUtils.isNotEmpty;

@Component
public abstract class AbstractExceptionHandler {

    @Value("${project.show-trace-exception}")
    private boolean showTraceException;

    @Autowired
    private MessageSource messageSource;

    protected ExceptionErrorDTO getBaseException(Exception ex) {
        ExceptionErrorDTO exceptionError = new ExceptionErrorDTO();

        exceptionError.setDate(LocalDateTime.now());
        exceptionError.setMessage(ex.getMessage());
        exceptionError.setTrace(getTrace(ex));
        exceptionError.setErrors(getErrors(ex));

        return exceptionError;
    }

    private List<String> getTrace(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();

        if (isNotEmpty(stackTrace) && showTraceException) {
            return Arrays.stream(stackTrace)
                    .map(StackTraceElement::toString)
                    .collect(Collectors.toList());
        }

        return null;
    }

    private List<ErrorDTO> getErrors(Exception ex) {
        if(ex instanceof BindException) {
            List<FieldError> fieldErrors = ((BindException) ex).getBindingResult().getFieldErrors();

            List<ErrorDTO> errors = new ArrayList<>();
            fieldErrors.forEach(e -> errors.add(getDtoByFieldError(e)));
            return errors;
        }

        return null;
    }

    private ErrorDTO getDtoByFieldError(FieldError fieldError) {
        String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
        return new ErrorDTO(fieldError.getField(), message);
    }
}
