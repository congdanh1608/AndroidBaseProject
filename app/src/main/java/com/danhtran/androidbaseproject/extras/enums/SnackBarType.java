package com.danhtran.androidbaseproject.extras.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SilverWolf on 09/03/2017.
 */
public enum SnackBarType {
    SUCCESS(0),
    ERROR(1),
    INFO(2),
    NORMAL(3),
    WARNING(4);

    private static Map map = new HashMap<>();
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
        return (SnackBarType) map.get(code);
    }
}
