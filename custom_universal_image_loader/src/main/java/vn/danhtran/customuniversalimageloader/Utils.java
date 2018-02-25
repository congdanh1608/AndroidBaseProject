package vn.danhtran.customuniversalimageloader;

import android.content.Context;
import android.net.Uri;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public class Utils {
    private static final String DRAWABLE_RESOURCE = "drawable://";
    private static final String ANDROID_RESOURCE = "android.resource://";
    private static final String FOREWARD_SLASH = "/";

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }

    public static String drawableToUri(Context context, int resourceId) {
        return DRAWABLE_RESOURCE + resourceId;
    }
}
