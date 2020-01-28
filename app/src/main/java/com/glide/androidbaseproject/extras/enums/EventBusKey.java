package com.glide.androidbaseproject.extras.enums;

/**
 * Created by danhtran on 09/04/2017.
 */

public enum EventBusKey {
    SIGN_IN_OUT("signInOut"),

    NOTIFICATION_SHOW("notification_show"),
    NOTIFICATION_HIDE("notification_hide"),

    APPLY_FILTER("apply_filter"),

    PERMISSION_CHANGE("permission_change"),
    GPS_ON_OFF("gps_on_off"),

    UPDATE_ACCOUNT_SCREEN("update_account_screen"),

    NOTIFICATION_OFFERS("notification_offers"),
    NOTIFICATION_SOLUTIONS("notification_solutions"),

    NETWORK_IS_AVAILABLE("network_is_available"),


    ;

    private final String value;

    EventBusKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static EventBusKey fromValue(String text) {
        for (EventBusKey b : EventBusKey.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
