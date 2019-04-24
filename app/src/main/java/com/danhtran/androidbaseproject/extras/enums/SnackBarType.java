package com.danhtran.androidbaseproject.extras.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by danhtran on 09/03/2017.
 */
public enum SnackBarType {
    SUCCESS(0),
    ERROR(1),
    INFO(2),
    NORMAL(3),
    WARNING(4);

    private static Map<Integer, SnackBarType> map = new HashMap<>();
    private int value;

    SnackBarType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for (SnackBarType snackbarType : SnackBarType.values()) {
            map.put(snackbarType.value, snackbarType);
        }
    }

    public static SnackBarType fromValue(int code) {
        return map.get(code);
    }
}
