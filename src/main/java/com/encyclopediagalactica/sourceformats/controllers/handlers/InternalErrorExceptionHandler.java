package com.encyclopediagalactica.sourceformats.controllers.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InternalErrorExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
          NullPointerException.class
      })
  protected ResponseEntity<Object> handleConflict(RuntimeException runtimeException, WebRequest webRequest) {
    return handleExceptionInternal(
        runtimeException,
        null,
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        webRequest);
  }

}
