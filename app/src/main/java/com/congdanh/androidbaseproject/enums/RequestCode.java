package com.congdanh.androidbaseproject.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danhtran on 09/03/2017.
 */
public enum RequestCode {
    DEFAULT(0),

    MENU_TOP(9989);

    private int value;
    private static Map map = new HashMap<>();

    private RequestCode(int value) {
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

    public static RequestCode valueOf(int code) {
        return (RequestCode) map.get(code);
    }
}
