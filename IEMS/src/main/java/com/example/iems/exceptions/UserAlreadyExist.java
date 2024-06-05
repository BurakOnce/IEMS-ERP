package com.example.iems.exceptions;

public class UserAlreadyExist extends Exception {
    private final String errorMessage;

    public UserAlreadyExist(String username) {
        super("User Already exist: " + username);
        this.errorMessage = "{\"error\": \"User Already exist\", \"username\": \"" + username + "\"}";
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
