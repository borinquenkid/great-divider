package com.objectcomputing.greatdivider.gui;

import java.util.Arrays;

public enum Operation {

    PLUS("+"), MINUS("-"), MULTI("*"), DIV("/"),DEC("."), CLEAR("C"), CALCULATE("=");

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }


    public static Operation getOperation(String string) {
        return Arrays.stream(values())
                .filter(it -> it.operation.equals(string))
                .findFirst()
                .orElse(null);

    }
}
