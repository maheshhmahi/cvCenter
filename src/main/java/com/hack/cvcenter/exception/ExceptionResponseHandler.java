package com.hack.cvcenter.exception;

import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Component
public class ExceptionResponseHandler {

    /**
     *
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @return
     */
    private static ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                                  @Nullable Object body,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status) {
        return new ResponseEntity(body, headers, status);
    }

    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST,  "Invalid Argument", ex.getLocalizedMessage()
                , request.getDescription(false));
        exceptionResponse.addFieldValidationErrors(ex.getBindingResult().getFieldErrors());
        exceptionResponse.addGlobalValidationErrors(ex.getBindingResult().getGlobalErrors());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
                                                     final WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Validation Error" , ex.getLocalizedMessage(),
                request.getDescription(false));
        exceptionResponse.addConstraintValidationErrors(ex.getConstraintViolations());
        return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param bindingResult
     * @return
     */
    ResponseEntity<Object> handleFormDataException(BindingResult bindingResult) {
        BindException ex = new BindException(bindingResult);

        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Validation Error", ex.getMessage());
        exceptionResponse.addFieldValidationErrors(bindingResult.getFieldErrors());
        exceptionResponse.addGlobalValidationErrors(bindingResult.getGlobalErrors());
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
