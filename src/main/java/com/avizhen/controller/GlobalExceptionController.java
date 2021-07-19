package com.avizhen.controller;

import com.avizhen.dto.ExceptionResponseDto;
import com.avizhen.exception.EmployeeServiceException;
import com.avizhen.exception.EmployeeServiceNotFoundException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionController {
    private static final Logger log = Logger.getLogger(GlobalExceptionController.class);
    @ExceptionHandler(value = EmployeeServiceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> resourceNotFound(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "NOT_FOUND");
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmployeeServiceException.class, RuntimeException.class})
    public ResponseEntity<ExceptionResponseDto> employeeServiceException(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "Ooooops!!!!!");
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponseDto buildExceptionResponse(Exception ex, String errorCode) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(new Date());
        return response;
    }
}