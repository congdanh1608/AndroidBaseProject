package com.danhtran.androidbaseproject.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DanhTran on 5/31/2019.
 */
public class ValidateUtils {
    /**
     * validation for email
     *
     * @param email email
     * @return boolean
     */
    public static boolean validateEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * validation for password
     * ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$
     * <p>
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
     * (?=\\S+$)          # no whitespace allowed in the entire string
     * .{4,}             # anything, at least six places though
     * $
     * <p>
     *
     * @param password password
     * @return boolean
     */

    public static boolean validatePassword(String password) {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
