package com.example.iems.model.Enum;

public enum QuantityUnit {
    KILOGRAM("Kilogram"),
    METER("Meter"),
    PIECE("Piece");

    private String Quantity;

    QuantityUnit(String quantity) {
        this.Quantity = quantity;
    }

    public String getDisplayName() {
        return Quantity;
    }
}
