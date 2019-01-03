package com.danhtran.androidbaseproject.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by SilverWolf on 26/09/2016.
 */
public class FontUtils {
    public static final String AVENIR_BLACK = "fonts/Avenir-Black.ttf";
    public static final String AVENIR_HEAVY = "fonts/Avenir-Heavy.ttf";
    public static final String AVENIR_MEDIUM = "fonts/Avenir-Medium.ttf";       //default

    public static Typeface getTypeFace(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }
}
