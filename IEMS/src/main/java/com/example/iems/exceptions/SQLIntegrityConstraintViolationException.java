package com.example.iems.exceptions;

public class SQLIntegrityConstraintViolationException extends Exception{
    public SQLIntegrityConstraintViolationException(String message) {
        super(message);
    }

}
