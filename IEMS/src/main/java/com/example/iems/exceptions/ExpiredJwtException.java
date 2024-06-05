package com.example.iems.exceptions;

public class ExpiredJwtException extends Exception{
    public ExpiredJwtException(String message) {
        super(message);
    }

}

