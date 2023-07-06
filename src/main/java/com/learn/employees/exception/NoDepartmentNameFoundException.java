package com.learn.employees.exception;

public class NoDepartmentNameFoundException extends Exception {
    public NoDepartmentNameFoundException(String msg) {
        super(msg);
    }
}
