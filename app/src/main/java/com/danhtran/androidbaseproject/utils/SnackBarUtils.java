package com.danhtran.androidbaseproject.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
     * @param isCenterText is center message text
     * @param snackBarType type of snack bar
     */
    public static void showSnackBar(View view, int text, boolean isCenterText, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(context.getString(text))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setStyle(builder, context, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack Bar
     *
     * @param view         view
     * @param text         message
     * @param isCenterText is center message text
     * @param snackBarType type of snack bar
     */
    public static void showSnackBar(View view, String text, boolean isCenterText, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(text)
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setStyle(builder, context, snackBarType);
        builder.build().show();
    }

    /**
     * Show Snack bar with action
     *
     * @param view            view
     * @param text            int message
     * @param actionText      int action message
     * @param onClickListener listener for action click event
     * @param snackBarType    snack type
     */
    public static void showSnackBarWithAction(View view, int text, int actionText,
                                              View.OnClickListener onClickListener, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(context.getString(text))
                .setActionText(actionText)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_SHORT);
        setStyle(builder, context, snackBarType);
        builder.build().show();
    }

    /**
     * Show indenfinite snack bar with action
     *
     * @param view            view
     * @param text            int message
     * @param actionText      int action message
     * @param onClickListener listener for action click event
     * @param snackBarType    snack bar type
     */
    public static void showIndenfiniteSnackBarWithAction(View view, int text, int actionText,
                                                         View.OnClickListener onClickListener, SnackBarType snackBarType) {
        Context context = view.getContext();
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(context.getString(text))
                .setActionText(context.getString(actionText))
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_INDEFINITE);
        setStyle(builder, context, snackBarType);
        builder.build().show();
    }

    /**
     * Show general error message
     *
     * @param parentView parentView
     * @param message    message text
     */
    public static void showGeneralError(View parentView, String message) {
        showSnackBar(parentView, message, true, SnackBarType.ERROR);
    }

    /**
     * Show general notify message
     *
     * @param parentView parent view
     * @param message    message text
     */
    public static void showGeneralNotify(View parentView, String message) {
        showSnackBar(parentView, message, true, SnackBarType.INFO);
    }

    //set style for snack bar
    private static void setStyle(Snacky.Builder builder, Context context, SnackBarType snackBarType) {
        switch (snackBarType) {
            case ERROR:
                builder.setBackgroundColor(ContextCompat.getColor(context, R.color.colorError));
                builder.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                builder.error();
                break;
            case INFO:
                builder.setBackgroundColor(ContextCompat.getColor(context, R.color.colorInfo));
                builder.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                builder.info();
                break;
            case SUCCESS:
                builder.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSuccess));
                builder.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                builder.success();
                break;
            case WARNING:
                builder.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWarning));
                builder.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                builder.warning();
                break;
        }
    }
}
