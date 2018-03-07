package com.congdanh.androidbaseproject.receiver;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.congdanh.androidbaseproject.utils.Utils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;


/**
 * Created by danh.tran on 04/08/16.
 */

public class LocationReceiver extends BroadcastReceiver implements OnLocationUpdatedListener {
    private Activity activity;
    private MyLocationCallback callback;
    private static LocationReceiver locationReceiver;

    public static LocationReceiver instance() {
        synchronized (LocationReceiver.class) {
            if (locationReceiver == null)
                locationReceiver = new LocationReceiver();
            return locationReceiver;
        }
    }

    public void requestLocation(Activity activity, MyLocationCallback callback) {
        // activity + callback
        this.callback = callback;
        this.activity = activity;

        Context context = activity.getApplicationContext();                     //must application context
        context.registerReceiver(this, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));      //must register in this to maintain activity + context


        // Getting GPS status
        boolean isGPSEnabled = SmartLocation.with(context).location().state().isGpsAvailable();

        // Getting network status
        boolean isNetworkEnabled = SmartLocation.with(context).location().state().isNetworkAvailable();

        if (!isGPSEnabled && !isNetworkEnabled) {
            showSettingsDialog();
        } else {
            requestPermissionGPS();
        }
    }

    private void startGetLocation() {
        SmartLocation.with(activity).location().oneFix().start(this);
    }

    public void stopUpdateLocation() {
        SmartLocation.with(activity).location().stop();
        activity = null;                //avoid leak memory
        callback = null;
    }

    @Override
    public void onLocationUpdated(Location location) {
        callback.locationChanged(true, location);
        stopUpdateLocation();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (activity != null && callback != null) {
            requestPermissionGPS();
        }
    }

    private void requestPermissionGPS() {
        Dexter.withActivity(activity)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            startGetLocation();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsPermissionDialog();
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
                        Logger.d(error);
                    }
                }).onSameThread().check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Need Enable GPS");
        builder.setMessage("This app needs GPS to use this feature. You can enable it in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                callback.locationChanged(false, null);
            }
        });
        builder.show();
    }

    private void showSettingsPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Utils.openSettings(activity, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public interface MyLocationCallback {
        void locationChanged(boolean success, Location location);
    }
}
