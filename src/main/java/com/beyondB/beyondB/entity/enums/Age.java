package com.beyondB.beyondB.entity.enums;

public enum Age {
    UNDER_7,
    AGE_7_10,
    AGE_11_13,
    AGE_14_16,
    AGE_17_19,
    OVER_18,
    ;

    public Age getLower(int steps) {
        int ordinal = this.ordinal() - steps;
        return ordinal >= 0 ? Age.values()[ordinal] : null;
    }

    public Age getHigher(int steps) {
        int ordinal = this.ordinal() + steps;
        Age[] ages = Age.values();
        return ordinal < ages.length ? ages[ordinal] : null;
    }

}
