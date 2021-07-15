package com.avizhen.exception;

public class EmployeeServiceNotFoundException extends RuntimeException {
    public EmployeeServiceNotFoundException(String message) {
        super(message);
    }

    public EmployeeServiceNotFoundException(Integer id) {
        super("Resource with id: " + id + " not found");
    }
}
