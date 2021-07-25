package com.avizhen.controller;

import com.avizhen.dto.ExceptionResponseDto;
import com.avizhen.exception.EmployeeServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(value = EmployeeServiceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> resourceNotFound(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "NOT_FOUND");
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseDto employeeServiceException(RuntimeException ex) {
        ExceptionResponseDto response = buildExceptionResponse(ex, "Ooooops!!!!!");
        log.error(ex.getMessage(), ex);
        return response;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> employeeBirthDateException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private ExceptionResponseDto buildExceptionResponse(Exception ex, String errorCode) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setErrorCode(errorCode);
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(new Date());
        return response;
    }
}