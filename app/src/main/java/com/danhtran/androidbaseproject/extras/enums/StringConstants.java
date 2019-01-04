package com.danhtran.androidbaseproject.extras.enums;


import com.danhtran.androidbaseproject.MyApplication;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public enum StringConstants {
    DATABASE_NAME("base_project"),
    ;

    public static final String PREFIX = MyApplication.Instance().getPackageName();

    private final String value;

    StringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return PREFIX + this.value;
    }
}
