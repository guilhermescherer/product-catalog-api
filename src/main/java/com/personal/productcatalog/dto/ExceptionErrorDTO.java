package com.personal.productcatalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Setter
@Getter
public class ExceptionErrorDTO {

    private Integer code;
    private LocalDateTime date;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> trace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorDTO> errors;
}
