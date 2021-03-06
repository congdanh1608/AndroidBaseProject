package com.congdanh.androidbaseproject.enums;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.utils.EnumUtils;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public enum SharePrefs {
    DEFAULT(""),

    SESSION_LOGIN("SESSION_LOGIN"),
    TOKEN_FIREBASE("TOKEN_FIREBASE"),
    PUSH_ID_FIREBASE("PUSH_ID_FIREBASE"),
    OFF_NOTIFY("OFF_NOTIFY"),
    LANGUAGE("LANGUAGE");

    public static final String PREFIX = MyApplication.Instance().getPackageName();

    private final String value;

    private SharePrefs(String value) {
        this.value = value;
    }

    public String toString() {
        return PREFIX + this.value;
    }

    public static SharePrefs fromValue(String value) {
        return EnumUtils.valueOf(SharePrefs.class, value);
    }
}
