package com.congdanh.androidbaseproject.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;

import com.congdanh.androidbaseproject.MyApplication;

/**
 * Created by congdanh on 17/06/2017.
 */

public class Utils {
    // navigating user to app settings
    public static void openSettings(Activity activity, int code) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", MyApplication.Instance().getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, code);
    }

    //open camera
    public static void openCamera(Activity activity, int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, code);
    }

    //Open url
    public static void openUrl(Activity activity, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    public static void goToPlayStore(Activity activity) {
        final String appPackageName = MyApplication.Instance().getPackageName(); // getPackageName() from Context or Activity object
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getUrlApp())));
        }
    }

    private static String getUrlApp() {
        final String appPackageName = MyApplication.Instance().getPackageName(); // getPackageName() from Context or Activity object
        return "https://play.google.com/store/apps/details?id=" + appPackageName;
    }

    /*public static void shareAppByMsg(Activity activity) {
        String message = getUrlApp();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        activity.startActivity(Intent.createChooser(share, activity.getString(R.string.share_app)));
    }

    public static void sendEmailTo(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{activity.getString(R.string.email_developer)});
        intent.putExtra(Intent.EXTRA_SUBJECT, "[" + activity.getString(R.string.app_name) + "] "
                + activity.getString(R.string.feedback));
        intent.putExtra(Intent.EXTRA_TEXT, "");

        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.send_email)));
    }*/

    public static void exitApp(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
