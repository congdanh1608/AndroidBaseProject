package com.danhtran.androidbaseproject.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by danhtran on 11/19/2018.
 */

public class TimeUtils {
    /**
     * Format moment ago for time
     *
     * implementation 'org.ocpsoft.prettytime:prettytime...'
     *
     * @return String
     */
    /*public static String getTimeInfo() {
        PrettyTime p = new PrettyTime(Locale.getDefault());
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.add(Calendar.MINUTE, -2);
        calendar.add(Calendar.SECOND, -40);
        String ago = p.format(calendar.getTime());
        return String.valueOf(ago);
    }*/

    /**
     * Get list name of monday in the past
     *
     * @param weekCount how many month from now you want to get
     * @return
     */
    public static String[] getNameOldMondays(int weekCount) {
        String[] days = new String[weekCount];

        DateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.add(Calendar.DAY_OF_YEAR, -(weekCount * 7));   //a week has 7 days

        int count = 0;
        do {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            // check if it is a Saturday or Sunday
            if (day == Calendar.MONDAY) {
                days[count] = format.format(calendar.getTime());
                count++;
            }
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        } while (count < weekCount);

        return days;
    }

    /**
     * Get list name of month in the past
     *
     * @return
     */
    public static String[] getNameOldMonthYTD() {
        int monthCount = numberOfMonthsUntillNow();
        String[] months = new String[monthCount];

        DateFormat format = new SimpleDateFormat("MMM", Locale.getDefault());
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        //<code>JANUARY</code> which is 0
        calendar.add(Calendar.MONTH, -monthCount + 1);

        int count = 0;
        do {
            months[count] = format.format(calendar.getTime());
            count++;
            calendar.add(Calendar.MONTH, 1);
        } while (count < monthCount);

        return months;
    }

    /**
     * get count of first month to now
     *
     * @return
     */
    public static int numberOfMonthsUntillNow() {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        //<code>JANUARY</code> which is 0
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * Return MONDAY of the weekIndex ago from the current time
     *
     * @param weekIndexAgo
     * @return
     */
    public static Date startDateOfWeekFromNow(int weekIndexAgo) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -weekIndexAgo * 7);
        return startDateOfWeek(cal.getTime());
    }

    /**
     * Return SUNDAY of the weekIndex ago from current time
     *
     * @param weekIndexAgo
     * @return
     */
    public static Date endDateOfWeekFromNow(int weekIndexAgo) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -weekIndexAgo * 7);
        return endDateOfWeek(cal.getTime());
    }

    /**
     * Return the MONDAY 00:00:00 of the week of the given date
     *
     * @param date
     * @return
     */
    private static Date startDateOfWeek(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
            dayOfWeek = dayOfWeek - 1;
        }

        // Make sure we return the MONDAY of the week
        cal.add(Calendar.DAY_OF_YEAR, -1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * Return the SUNDAY 23:59:59 of the week of the given date
     *
     * @param date
     * @return
     */
    private static Date endDateOfWeek(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek != Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_YEAR, +1);
            dayOfWeek = dayOfWeek + 1;
        }

        // Make sure we return the SUNDAY of the week
        cal.add(Calendar.DAY_OF_YEAR, +1);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        return cal.getTime();
    }

    public static Date startDateOfMonthFromNow(int monthIndexAgo) {

        Calendar calendar = Calendar.getInstance();
        int curMonth = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, curMonth - monthIndexAgo + 1);

        //The first day of the month has value 1.
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    public static Date endDateOfMonthFromNow(int monthIndexAgo) {

        Calendar calendar = Calendar.getInstance();
        int curMonth = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, curMonth - monthIndexAgo + 2);

        //The first day of the month has value 1.
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }
}
