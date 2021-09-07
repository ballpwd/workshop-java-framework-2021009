package com.example.demoapp.employees;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message) ;
    }
}
