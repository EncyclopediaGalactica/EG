package com.encyclopediagalactica.sourceformats.controllers.handlers;

import java.util.NoSuchElementException;
import com.encyclopediagalactica.sourceformats.errors.ErrorMessages;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NoSuchElementNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
          NoSuchElementException.class,
          EmptyResultDataAccessException.class
      })
  protected ResponseEntity<Object> handleConflict(RuntimeException runtimeException, WebRequest webRequest) {
    return handleExceptionInternal(
        runtimeException,
        ErrorMessages.NO_SUCH_ELEMENT,
        new HttpHeaders(),
        HttpStatus.NOT_FOUND,
        webRequest);
  }
}
