package com.personal.productcatalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Setter
@Getter
public class ExceptionErrorDTO {

    private static final int MAX_SIZE_TRACE = 5;

    private Integer code;
    private LocalDateTime date;
    private String message;
    @Setter(AccessLevel.NONE)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorDTO> errors;

    public void setTrace(List<String> trace) {
        if(nonNull(trace) && trace.size() <= MAX_SIZE_TRACE) {
            this.trace = trace;
        }
    }
}
