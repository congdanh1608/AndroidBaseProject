package com.danhtran.androidbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.danhtran.androidbaseproject.enums.SnackbarType;

import de.mateware.snacky.Snacky;


/**
 * Created by SilverWolf on 04/08/2017.
 */

public class SnackBarUtils {
    public static void showSnackBar(View view, int text, int textColor, boolean isCenterText, SnackbarType snackbarType) {
        showSnackBar(view, view.getContext().getString(text), textColor, isCenterText, snackbarType);
    }

    //use view
    public static void showSnackBar(View view, String text, int textColor, boolean isCenterText, SnackbarType snackbarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setTypeTemplate(builder, snackbarType);
        builder.build().show();
    }

    public static void showSnackBar(Activity activity, int text, int textColor, boolean isCenterText, SnackbarType snackbarType) {
        showSnackBar(activity, activity.getString(text), textColor, isCenterText, snackbarType);
    }

    //use activity
    public static void showSnackBar(Activity activity, String text, int textColor, boolean isCenterText, SnackbarType snackbarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setDuration(Snacky.LENGTH_SHORT);
        if (isCenterText) builder.centerText();
        setTypeTemplate(builder, snackbarType);
        builder.build().show();
    }

    public static void showSnackBarWithAction(View view, int text, int textColor, int actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackbarType snackbarType) {
        Context context = view.getContext();
        showSnackBarWithAction(view, context.getString(text), textColor, context.getString(actionColor),
                actionColor, onClickListener, snackbarType);
    }

    //add action text
    public static void showSnackBarWithAction(View view, String text, int textColor, String actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackbarType snackbarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setView(view)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(actionText)
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_SHORT);
        setTypeTemplate(builder, snackbarType);
        builder.build().show();
    }

    public static void showSnackBarWithAction(Activity activity, int text, int textColor, int actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackbarType snackbarType) {
        showSnackBarWithAction(activity, activity.getString(text), textColor, activity.getString(actionText),
                actionColor, onClickListener, snackbarType);
    }

    public static void showSnackBarWithAction(Activity activity, String text, int textColor, String actionText, int actionColor,
                                              View.OnClickListener onClickListener, SnackbarType snackbarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(actionText)
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_SHORT);
        setTypeTemplate(builder, snackbarType);
        builder.build().show();
    }

    public static void showSnackBarWithActionIndenfinite(Activity activity, int text, int textColor, int actionText, int actionColor,
                                                         View.OnClickListener onClickListener, SnackbarType snackbarType) {
        Snacky.Builder builder = Snacky.builder();
        builder.setActivity(activity)
                .setText(text)
                .setTextColor(DeprecatedUtils.getResourceColor(textColor))
                .setActionText(actionText)
                .setActionTextColor(actionColor)
                .setActionClickListener(onClickListener)
                .setDuration(Snacky.LENGTH_INDEFINITE);
        setTypeTemplate(builder, snackbarType);
        builder.build().show();
    }


    //set template for snackbar
    private static void setTypeTemplate(Snacky.Builder builder, SnackbarType snackbarType) {
        switch (snackbarType) {
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
