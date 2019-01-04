package com.danhtran.androidbaseproject.utils;

import java.util.Map;

/**
 * Created by SilverWolf on 29/11/2016.
 */

//Default return first ENUM -> Default("")  --> to void crash for some function
public class EnumUtils {
    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String value, final boolean caseSensitive) {
        final T[] enumConstants = enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (final T enumConstant : enumConstants) {
            if (caseSensitive) {
                if (enumConstant.toString().equals(value)) {
                    return enumConstant;
                }
            } else {
                if (enumConstant.toString().equalsIgnoreCase(value)) {
                    return enumConstant;
                }
            }
        }
        return enumConstants[0];
    }

    /**
     * Get enum from string value
     *
     * @param enumType enum
     * @param value    value
     * @param <T>      type of enum
     * @return enum
     */
    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final String value) {
        final T[] enumConstants = enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (final T enumConstant : enumConstants) {
            if (enumConstant.toString().equals(value)) {
                return enumConstant;
            }
        }
        return enumConstants[0];
    }

    /**
     * Get enum from value
     *
     * @param value value
     * @param map   map of enum
     * @param <T>   Type of enum
     * @return enum
     */
    public static <T extends Enum<T>> T valueOf(int value, Map<Integer, T> map) {
        if (map.get(value) != null) {
            return map.get(value);
        }
        return map.get(0);
    }
}
