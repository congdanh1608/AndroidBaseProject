package com.congdanh.androidbaseproject.enums;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public enum Sub {
    DEFAULT(""),

    DEVICES("devices"),
    AUTHENETICATE_MAIL("authenticateEmail"),
    JOIN_EVENT_DAY("checkin");

    private final String value;

    private Sub(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
