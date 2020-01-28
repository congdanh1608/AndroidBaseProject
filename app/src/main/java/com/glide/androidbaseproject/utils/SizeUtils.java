package com.glide.androidbaseproject.utils;


import com.glide.androidbaseproject.MyApplication;

/**
 * Created by danhtran on 07/07/2016.
 */

//Performance calculate convert dp, px.
public class SizeUtils {
    private static boolean isInitialised = false;
    private static float pixelsPerOneDp;

    private static void initialise() {
        pixelsPerOneDp = MyApplication.instance().getApplicationContext()
                .getResources().getDisplayMetrics().densityDpi / 160f;
        isInitialised = true;
    }

    /**
     * Convert PX to DP
     *
     * @param px px
     * @return dp
     */
    public static int pxToDp(int px) {
        if (!isInitialised) {
            initialise();
        }
        return (int) (px / pixelsPerOneDp);
    }

    /**
     * Convert DP to PX
     *
     * @param dp dp
     * @return px
     */
    public static int dpToPx(int dp) {
        if (!isInitialised) {
            initialise();
        }
        return (int) (dp * pixelsPerOneDp);
    }
}
