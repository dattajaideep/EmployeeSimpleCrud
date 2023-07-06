package com.learn.employees.exception;

public class NoEmployeeFoundException extends Exception {
    public NoEmployeeFoundException(String msg) {
        super(msg);
    }
}
