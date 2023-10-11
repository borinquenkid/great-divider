package com.objectcomputing.greatdivider;

import com.google.common.base.Enums;

public enum DivisionWarningEnum {
    OK(""),
    DIV_BY_ZERO("Can not divide by zero"),
    BLANKS("Fill in both dividend and divisor");

    public String getMessage() {
        return message;
    }

    private final String message;

    DivisionWarningEnum(String message) {
        this.message = message;
    }

    public static DivisionWarningEnum getIfPresent(String string) {
        return Enums.getIfPresent(DivisionWarningEnum.class, string).orNull();
    }
}
