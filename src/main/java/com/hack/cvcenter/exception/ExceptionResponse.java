package com.hack.cvcenter.exception;

import jakarta.validation.ConstraintViolation;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class ExceptionResponse {

    HttpStatus httpStatus;
    Integer statusCode;
    LocalDateTime timestamp;
    String errorDescription;
    String cause;
    List<ApiValidationError> errors;
    String path;

    /**
     *
     * @param httpStatus
     * @param cause
     * @param errorDescription
     * @param path
     */
    ExceptionResponse(HttpStatus httpStatus, String cause, String errorDescription, String path) {
        this.httpStatus = httpStatus;
        this.statusCode = httpStatus.value();
        this.errorDescription = errorDescription;
        this.path = path;
        this.timestamp = LocalDateTime.now();
        this.cause = cause;
    }

    /**
     *
     * @param httpStatus
     * @param cause
     * @param errorDescription
     */
    ExceptionResponse(HttpStatus httpStatus, String cause, String errorDescription) {
        this.httpStatus = httpStatus;
        this.statusCode = httpStatus.value();
        this.errorDescription = errorDescription;
        this.timestamp = LocalDateTime.now();
        this.cause = cause;
    }

    /**
     *
     * @param subError
     */
    private void addErrorDetail(ApiValidationError subError) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(subError);
    }

    /**
     *
     * @param object
     * @param field
     * @param rejectedValue
     * @param message
     */
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addErrorDetail(new ApiValidationError(object, message, field, rejectedValue));
    }

    /**
     *
     * @param object
     * @param message
     */
    private void addValidationError(String object, String message) {
        addErrorDetail(new ApiValidationError(object, message));
    }

    /**
     *
     * @param fieldError
     */
    private void addFieldValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    /**
     *
     * @param fieldErrors
     */
    void addFieldValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.stream().forEach(fieldError -> {
            addFieldValidationError(fieldError);
        });
    }

    /**
     *
     * @param objectError
     */
    private void addGlobalValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    /**
     *
     * @param globalErrors
     */
    void addGlobalValidationErrors(List<ObjectError> globalErrors) {
        globalErrors.stream().forEach(globalError -> {
            addGlobalValidationError(globalError);
        });
    }

    /**
     *
     * @param cv
     */
    private void addConstraintValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    /**
     *
     * @param constraintViolations
     */
    void addConstraintValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.stream().forEach(constraintViolation -> {
            addConstraintValidationError(constraintViolation);
        });
    }

}
