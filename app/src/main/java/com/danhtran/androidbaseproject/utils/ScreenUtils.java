package com.danhtran.androidbaseproject.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.danhtran.androidbaseproject.MyApplication;

/**
 * Created by danhtran on 2/28/2018.
 */

public class ScreenUtils {
    private final static int SDK_VERSION = Build.VERSION.SDK_INT;
    private static int DEVICE_WIDTH = 0;
    private static int DEVICE_HEIGHT = 0;

    private static void getScreenSize() {
        if (DEVICE_WIDTH != 0 && DEVICE_HEIGHT != 0) {
            return;
        }

        WindowManager windowManager = ((WindowManager) MyApplication.instance().getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();

            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            int widthPixels = metrics.widthPixels;
            int heightPixels = metrics.heightPixels;

            if (SDK_VERSION >= Build.VERSION_CODES.ICE_CREAM_SANDWICH &&
                    SDK_VERSION < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    widthPixels = (Integer) Display.class.getMethod("getWidth")
                            .invoke(display);
                    heightPixels = (Integer) Display.class.getMethod("getHeight")
                            .invoke(display);

                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            // includes window decorations (statusbar bar/menu bar)
            else if (SDK_VERSION >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    Point realSize = new Point();
                    Display.class.getMethod("getSize", Point.class).invoke(display,
                            realSize);
                    widthPixels = realSize.x;
                    heightPixels = realSize.y;
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            } else {
                widthPixels = display.getWidth();
                heightPixels = display.getHeight();
            }
            DEVICE_WIDTH = widthPixels;
            DEVICE_HEIGHT = heightPixels;
        }
    }

    /**
     * Get width of screen in PX
     *
     * @return width
     */
    public static int getWidthScreenInPX() {
        getScreenSize();
        return DEVICE_WIDTH;
    }

    /**
     * Get height of screen in PX
     *
     * @return height
     */
    public static int getHeightScreenInPX() {
        getScreenSize();
        return DEVICE_HEIGHT;
    }

    /**
     * Get width of screen in DP
     *
     * @return width
     */
    public static int getWidthScreenInDP() {
        getScreenSize();
        return SizeUtils.pxToDp(DEVICE_WIDTH);
    }

    /**
     * Get height of screen in DP
     *
     * @return height
     */
    public static int getHeightScreenInDP() {
        getScreenSize();
        return SizeUtils.pxToDp(DEVICE_HEIGHT);
    }
}
