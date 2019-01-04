package com.danhtran.androidbaseproject.extras.enums;

import com.danhtran.androidbaseproject.utils.EnumUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danhtran on 09/03/2017.
 */
public enum RequestCode {
    MENU_TOP(9989);

    private int value;
    private static Map<Integer, RequestCode> map = new HashMap<>();

    RequestCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for (RequestCode requestCode : RequestCode.values()) {
            map.put(requestCode.value, requestCode);
        }
    }

    public static RequestCode fromValue(int code) {
        return EnumUtils.valueOf(code, map);
    }
}
