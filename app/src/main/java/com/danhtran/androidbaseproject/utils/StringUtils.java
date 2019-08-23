package com.danhtran.androidbaseproject.utils;

import java.text.DecimalFormat;

/**
 * Created by danhtran on 1/4/2019.
 */
public class StringUtils {
    public static String formatDecimal(double value) {

        String pattern = "#,###.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);

        return decimalFormat.format(value);
    }

    public static String formatDecimal2(double value) {

        String pattern = "#,#00.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setGroupingSize(3);

        return decimalFormat.format(value);
    }
}
