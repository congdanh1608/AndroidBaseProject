package com.danhtran.androidbaseproject.extras.enums;

import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.utils.EnumUtils;

/**
 * Created by danhtran on 09/04/2017.
 */

public enum SharePrefs {
    IS_NOT_FIRST_VIEW("is_not_first_view"),

    SESSION_LOGIN("SESSION_LOGIN"),
    TOKEN_FIREBASE("TOKEN_FIREBASE"),
    PUSH_ID_FIREBASE("PUSH_ID_FIREBASE"),
    OFF_NOTIFY("OFF_NOTIFY"),
    LANGUAGE("LANGUAGE");

    public static final String PREFIX = MyApplication.instance().getPackageName();

    private final String value;

    SharePrefs(String value) {
        this.value = value;
    }

    public String getValue() {
        return PREFIX + this.value;
    }

    public static SharePrefs fromValue(String value) {
        return EnumUtils.valueOf(SharePrefs.class, value);
    }
}
