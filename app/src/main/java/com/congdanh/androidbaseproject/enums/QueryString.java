package com.congdanh.androidbaseproject.enums;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public enum QueryString {
    DEFAULT(""),

    ORIGIN("origin"),
    DESTINATION("destination"),
    MODE("mode"),
    KEY("key");

    private final String value;

    private QueryString(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
