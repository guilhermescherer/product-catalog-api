package com.personal.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {

    private String field;
    private String message;

    public ErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
