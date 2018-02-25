package com.congdanh.androidbaseproject.utils;


import com.congdanh.androidbaseproject.MyApplication;

/**
 * Created by CongDanh on 07/07/2016.
 */

//Performance calculate convert dp, px.
public class DimensionUtils {
    private static boolean isInitialised = false;
    private static float pixelsPerOneDp;

    private static void initialise() {
        pixelsPerOneDp = MyApplication.Instance().getApplicationContext()
                .getResources().getDisplayMetrics().densityDpi / 160f;
        isInitialised = true;
    }

    public static int pxToDp(int px) {
        if (!isInitialised) {
            initialise();
        }
        return (int) (px / pixelsPerOneDp);
    }

    public static int dpToPx(int dp) {
        if (!isInitialised) {
            initialise();
        }
        return (int) (dp * pixelsPerOneDp);
    }
}
