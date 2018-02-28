package com.congdanh.androidbaseproject.enums;


import com.congdanh.androidbaseproject.MyApplication;

/**
 * Created by congdanh on 09/04/2017.
 */

public enum StringConstants {
    DEFAULT(""),

    DATABASE_NAME("base_project");

    public static final String PREFIX = MyApplication.Instance().getPackageName();

    private final String value;

    private StringConstants(String value) {
        this.value = value;
    }

    public String toString() {
        return PREFIX + this.value;
    }
}
