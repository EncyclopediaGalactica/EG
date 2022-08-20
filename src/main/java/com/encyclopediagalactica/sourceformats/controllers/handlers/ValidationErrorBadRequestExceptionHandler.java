package com.encyclopediagalactica.sourceformats.controllers.handlers;

import javax.validation.ConstraintViolationException;
import com.encyclopediagalactica.sourceformats.errors.ErrorMessages;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationErrorBadRequestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
          ConstraintViolationException.class,
          InvalidDataAccessApiUsageException.class,
          DataIntegrityViolationException.class
      })
  protected ResponseEntity<Object> handleConflict(RuntimeException runtimeException, WebRequest webRequest) {
    return handleExceptionInternal(
        runtimeException,
        ErrorMessages.VALIDATION_ERROR,
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        webRequest);
  }

}
