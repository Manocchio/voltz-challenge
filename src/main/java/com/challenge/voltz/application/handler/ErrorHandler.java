package com.challenge.voltz.application.handler;

import com.challenge.voltz.application.web.exceptions.ErrorV1;
import com.challenge.voltz.domain.exceptions.InvalidToolException;
import com.challenge.voltz.domain.exceptions.ToolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ToolNotFoundException.class)
    public static ResponseEntity<Object> exception(ToolNotFoundException ex) {
        return new ResponseEntity<>(new ErrorV1(
                ex.getMessage(),
                ex.getStackTrace()[0].getClassName(),
                HttpStatus.NOT_FOUND.value()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidToolException.class)
    public static ResponseEntity<Object> exception(InvalidToolException ex) {
        return new ResponseEntity<>(new ErrorV1(
                ex.getMessage(),
                ex.getStackTrace()[0].getClassName(),
                HttpStatus.BAD_REQUEST.value()
        ), HttpStatus.BAD_REQUEST);
    }

}
