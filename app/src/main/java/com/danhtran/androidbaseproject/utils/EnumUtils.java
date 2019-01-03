package com.danhtran.androidbaseproject.utils;

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

    public static <T extends Enum<T>> T valueOf(final Class<T> enumType, final int value) {
        final T[] enumConstants = enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (final T enumConstant : enumConstants) {
            if (Integer.valueOf(enumConstant.toString()) == value) {
                return enumConstant;
            }
        }
        return enumConstants[0];
    }
}
