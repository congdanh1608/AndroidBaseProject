package com.danhtran.androidbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.extras.enums.SnackBarType;

import de.mateware.snacky.Snacky;


/**
 * Created by danhtran on 04/08/2017.
 */

public class SnackBarUtils {
    /**
     * Show Snack Bar
     *
     * @param view         view
     * @param text         int message
     * @param textColor    int message color
     * @param isCenterText is center message text
     * @param snackBarType type of snack bar
     */
    public static void showSnackBar(View view, int text, int textColor, boolean isCenterText, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(context.getString(text))
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack Bar
     *
     * @param activity     activity
     * @param text         int message
     * @param textColor    int message color
     * @param isCenterText is center message text
     * @param snackBarType type of snack bar
     */
    public static void showSnackBar(Activity activity, int text, int textColor, boolean isCenterText, SnackBarType snackBarType) {
        Context context = activity.getBaseContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(context.getString(text))
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack Bar
     *
     * @param activity     Activity
     * @param text         String message
     * @param textColor    int message color
     * @param isCenterText is center message text
     * @param snackBarType type of snack bar
     */
    public static void showSnackBar(Activity activity, String text, int textColor, boolean isCenterText, SnackBarType snackBarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack bar with action
     *
     * @param view            view
     * @param text            int message
     * @param textColor       int message color
     * @param actionText      int action message
     * @param actionColor     int action color
     * @param onClickListener listener for action click event
     * @param snackBarType    snack type
     */
    public static void showSnackBarWithAction(View view, int text, int textColor, int actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(context.getString(text))
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(actionText)
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_SHORT);
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack bar with action
     *
     * @param activity        activity
     * @param text            int message
     * @param textColor       int message color
     * @param actionText      int action message
     * @param actionColor     int action color
     * @param onClickListener listener for action click event
     * @param snackBarType    snack type
     */
    public static void showSnackBarWithAction(Activity activity, int text, int textColor, int actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackBarType snackBarType) {
        Context context = activity.getBaseContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(context.getString(text))
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(context.getString(actionText))
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_SHORT);
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show indenfinite snack bar with action
     *
     * @param activity        activity
     * @param text            int message
     * @param textColor       int message color
     * @param actionText      int action message
     * @param actionColor     int action color
     * @param onClickListener listener for action click event
     * @param snackBarType    snack bar type
     */
    public static void showIndenfiniteSnackBarWithAction(Activity activity, int text, int textColor, int actionText, int actionColor,
                                                         View.OnClickListener onClickListener, SnackBarType snackBarType) {
        Context context = activity.getBaseContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(context.getString(text))
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(context.getString(actionText))
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_INDEFINITE);
        setTypeTemplate(builder, snackBarType);
        builder.build().show();
    }

    /**
     * Show general error message
     *
     * @param context context
     * @param message message text
     */
    public static void showGeneralError(Context context, String message) {
        showSnackBar((Activity) context, message, R.color.colorWhite, true, SnackBarType.ERROR);
    }

    /**
     * Show general notify message
     *
     * @param context context
     * @param message message text
     */
    public static void showGeneralNotify(Context context, String message) {
        showSnackBar((Activity) context, message, R.color.colorWhite, true, SnackBarType.INFO);
    }

    //set template for snackbar
    private static void setTypeTemplate(Snacky.Builder builder, SnackBarType snackBarType) {
        switch (snackBarType) {
            case ERROR:
                builder.error();
                break;
            case INFO:
                builder.info();
                break;
            case NORMAL:
                break;
            case SUCCESS:
                builder.success();
                break;
            case WARNING:
                builder.warning();
                break;
        }
    }
}
