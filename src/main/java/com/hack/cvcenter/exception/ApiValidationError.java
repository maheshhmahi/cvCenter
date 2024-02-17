package com.hack.cvcenter.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiValidationError {

    String object;
    String field;
    Object rejectedValue;
    String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    ApiValidationError(String object, String message, String field, Object rejectedValue) {
        this(object, message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }
}
