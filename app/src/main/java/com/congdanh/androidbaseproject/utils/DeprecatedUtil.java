package com.congdanh.androidbaseproject.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.congdanh.androidbaseproject.MyApplication;

import java.util.Locale;


/**
 * Created by danhtran on 15/10/2016.
 */
public class DeprecatedUtil {

    public static int getResourceColor(int resource, Context context) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.LOLLIPOP) {
            return ContextCompat.getColor(context, resource);
        } else {
            return context.getResources().getColor(resource);
        }
    }

    public static Drawable getResourceDrawable(int resource, Context context) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resource, context.getTheme());
        } else {
            return context.getResources().getDrawable(resource);
        }
    }

    public static void setLocale(Configuration configuration, Locale myLocale) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.N) {
            configuration.setLocale(myLocale);
        } else {
            configuration.locale = myLocale;
        }
    }

    public static Locale getLocale(Resources resources) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.N) {
            return resources.getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return resources.getConfiguration().locale;
        }
    }

    public static void removeOnGlobalLayoutListener(ViewTreeObserver obs, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.JELLY_BEAN) {
            obs.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            obs.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Spanned fromHtml(String text) {
        Spanned result = null;
        if (!TextUtils.isEmpty(text)) {
            if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.N) {
                result = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.fromHtml(text);
            }
        }
        return result;
    }

    public static String toHtml(Spanned text) {
        String result = "";
        if (!TextUtils.isEmpty(text)) {
            if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.N) {
                result = Html.toHtml(text, Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.toHtml(text);
            }
        }
        return result;
    }

    //get resource vector or png
    public static int getImageVector(int resourceVector, int resourcePNG) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.LOLLIPOP) {     //use vector
            return resourceVector;
        } else {                                                         //use png
            return resourcePNG;
        }
    }

    public static Drawable getDrawableVector(Context context, int resourceVector, int resourcePNG) {
        return getResourceDrawable(getImageVector(resourceVector, resourcePNG), context);
    }

    public static void removeRule(RelativeLayout.LayoutParams layoutParams, int verb) {
        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.removeRule(verb);
        } else {
            layoutParams.addRule(verb, 0);
        }
    }
}
