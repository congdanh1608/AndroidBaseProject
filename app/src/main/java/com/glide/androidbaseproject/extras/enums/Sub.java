package com.glide.androidbaseproject.extras.enums;

/**
 * Created by danhtran on 09/04/2017.
 */

public enum Sub {
    DEVICES("devices"),
    AUTHENETICATE_MAIL("authenticateEmail"),
    JOIN_EVENT_DAY("checkin");

    private final String value;

    Sub(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
