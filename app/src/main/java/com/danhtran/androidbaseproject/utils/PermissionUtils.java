package com.danhtran.androidbaseproject.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.extras.Constant;
import com.danhtran.androidbaseproject.extras.enums.EventBusKey;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by DanhTran on 5/8/2019.
 */
public class PermissionUtils {
    public static final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    public static final int PERMISSION_GRANTED = 0;
    public static final int PERMISSION_DENIED = 1;
    public static final int PERMISSION_BLOCK_OR_NEVER_ASKED = 2;

    /**
     * ic_check_green permission for app
     *
     * @param activity
     * @param permissions
     * @return
     */
    private static int getPermissionsStatus(Activity activity, String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity != null && permissions != null) {
                for (String permission : permissions) {
                    if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                            return PERMISSION_BLOCK_OR_NEVER_ASKED;
                        }
                        return PERMISSION_DENIED;
                    }
                }
                return PERMISSION_GRANTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return PERMISSION_GRANTED;
    }

    public static int getLocationPermissionsStatus(Activity activity) {
        return getPermissionsStatus(activity, PERMISSIONS);
    }

    public static void requestLocationPermission(final Activity activity, final RequestPermissionListener listener) {
        Dexter.withActivity(activity)
                .withPermissions(PermissionUtils.PERMISSIONS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            listener.areAllPermissionGranted();
                            EventBus.getDefault().post(EventBusKey.UPDATE_ACCOUNT_SCREEN.getValue());
                        }
                        if (report.isAnyPermissionPermanentlyDenied() || !report.getDeniedPermissionResponses().isEmpty()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog(activity, listener);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(activity, "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                }).onSameThread().check();
    }

    private static void showSettingsDialog(final Activity activity, final RequestPermissionListener listener) {
        DialogUtils.showDialog(activity,
                R.string.permission_location_title,
                R.string.permission_location_dialog_location_content,
                R.string.permission_location_positive_btn, R.string.permission_location_nagative_btn,
                new DialogUtils.DialogListener() {
                    @Override
                    public void positiveClick() {
                        Utils.openSettings(activity, Constant.REQUEST_CODE_RESULT_LOCATION_PERMISSION);
                    }

                    @Override
                    public void negativeClick() {
                        listener.isAnyPermissionDenied();
                    }
                });
    }

    public interface RequestPermissionListener {
        void areAllPermissionGranted();

        void isAnyPermissionDenied();
    }
}
