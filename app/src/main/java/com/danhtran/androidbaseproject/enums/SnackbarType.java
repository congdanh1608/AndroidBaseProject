package com.danhtran.androidbaseproject.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SilverWolf on 09/03/2017.
 */
public enum SnackbarType {
    SUCCESS(0),
    ERROR(1),
    INFO(2),
    NORMAL(3),
    WARNING(4);

    private static Map map = new HashMap<>();
    private int value;

    SnackbarType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for (SnackbarType snackbarType : SnackbarType.values()) {
            map.put(snackbarType.value, snackbarType);
        }
    }

    public static SnackbarType valueOf(int code) {
        return (SnackbarType) map.get(code);
    }
}
