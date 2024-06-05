package com.example.iems.model.Enum;

public enum OrderStatus {

    PENDING("Pending"),
    DURATION("Duration"),
    COMPLETE("Complete"),
    UNSUCCESSFUL("Unsuccessful"),
    CONFIRMED("Confirmed");



    private String Status;

    OrderStatus(String status) {
        this.Status = status;
    }

    public String getStatusName() {
        return Status;
    }
}
