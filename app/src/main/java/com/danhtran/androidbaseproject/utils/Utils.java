package com.danhtran.androidbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;

import com.danhtran.androidbaseproject.MyApplication;
import com.orhanobut.logger.Logger;

import java.security.MessageDigest;

/**
 * Created by danhtran on 17/06/2017.
 */

public class Utils {
    /**
     * navigating user to app settings
     *
     * @param activity activity
     * @param code     request code
     */
    public static void openSettings(Activity activity, int code) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", MyApplication.instance().getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, code);
    }

    /**
     * open camera
     *
     * @param activity activity
     * @param code     request code
     */
    public static void openCamera(Activity activity, int code) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, code);
    }

    /**
     * open URL
     *
     * @param activity activity
     * @param url      url
     */
    public static void openUrl(Activity activity, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    /**
     * Open play store
     *
     * @param activity activity
     */
    public static void goToPlayStore(Activity activity) {
        final String appPackageName = MyApplication.instance().getPackageName(); // getPackageName() from Context or Activity object
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getUrlApp())));
        }
    }

    //get url of this app with package name
    private static String getUrlApp() {
        final String appPackageName = MyApplication.instance().getPackageName(); // getPackageName() from Context or Activity object
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

    public static void generalSHAKey(Context context) {
        try {
            //by facebook sdk
            /*FacebookSdk.sdkInitialize(getApplicationContext());
            Log.d("AppLog", "key:" + FacebookSdk.getApplicationSignature(this));*/
            //or without facebook sdk
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Logger.d("SHA KEY = " + hashKey);
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

    }
}
