package com.congdanh.androidbaseproject.enums;

import com.congdanh.androidbaseproject.utils.EnumUtils;

/**
 * Created by danhtran on 09/03/2017.
 */
public enum RequestCode {
    DEFAULT(0),
    MY_PERMISSIONS_REQUEST_GPS(9988),
    RC_GG_SIGN_IN(9989);

    private int value;

    private RequestCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RequestCode fromValue(int value) {
        return EnumUtils.valueOf(RequestCode.class, value);
    }
}
