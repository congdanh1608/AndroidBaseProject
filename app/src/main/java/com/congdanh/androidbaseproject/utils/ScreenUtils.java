package com.congdanh.androidbaseproject.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.congdanh.androidbaseproject.MyApplication;

/**
 * Created by congdanh on 2/28/2018.
 */

public class ScreenUtils {
    private static int DEVICE_WIDTH = 0;
    private static int DEVICE_HEIGHT = 0;

    public static void getScreenSize() {
        if (DEVICE_WIDTH != 0 && DEVICE_HEIGHT != 0) {
            return;
        }
        Display display = ((WindowManager) MyApplication.Instance().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                MyApplication.Instance().getVersionOS() < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getWidth")
                        .invoke(display);
                heightPixels = (Integer) Display.class.getMethod("getHeight")
                        .invoke(display);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        else if (MyApplication.Instance().getVersionOS() >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getSize", Point.class).invoke(display,
                        realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        } else {
            widthPixels = display.getWidth();
            heightPixels = display.getHeight();
        }
        DEVICE_WIDTH = widthPixels;
        DEVICE_HEIGHT = heightPixels;

    }

    public static int getWidthScreenInPX() {
        getScreenSize();
        return DEVICE_WIDTH;
    }

    public static int getHeightScreenInPX() {
        getScreenSize();
        return DEVICE_HEIGHT;
    }

    public static int getWidthScreenInDP() {
        getScreenSize();
        return SizeUtils.pxToDp(DEVICE_WIDTH);
    }

    public static int getHeightScreenInDP() {
        getScreenSize();
        return SizeUtils.pxToDp(DEVICE_WIDTH);
    }
}
