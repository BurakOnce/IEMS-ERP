package com.example.iems.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProblemDetails {
    @JsonProperty("message")
    private String message;

    public ProblemDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
