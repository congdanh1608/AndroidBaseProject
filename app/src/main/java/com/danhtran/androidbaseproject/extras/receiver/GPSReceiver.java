package com.danhtran.androidbaseproject.extras.receiver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.extras.Constant;
import com.danhtran.androidbaseproject.extras.enums.EventBusKey;
import com.danhtran.androidbaseproject.utils.DialogUtils;
import com.google.android.gms.location.LocationRequest;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by danh.tran on 04/08/16.
 */

public class GPSReceiver extends BroadcastReceiver {
    private static final int TYPE_REQUEST_LAST_KNOW = 0;
    private static final int TYPE_REQUEST_UPDATE = 1;

    private static final long TIME_GPS_UPDATE = 1000 * 10;  //10 secs       --> real every 2s
    private static final long MIN_DISTANCE_MOVING = 10;     //10 meters

    private Context appContext;
    private Context context;
    private GPSReceiverListener listener;
    private int currentType;

    //disposable
    private Disposable disposable;
    private Disposable disposable2;
    private Disposable disposableUpdated;

    private ReactiveLocationProvider locationProvider;
    private LocationRequest locationRequest;

    private boolean isFirstReceive = true;

    //last current location
    private Location lastLocation;

    public GPSReceiver(Context context, GPSReceiverListener listener) {
        this.listener = listener;

        this.context = context;
        this.appContext = context.getApplicationContext();                     //must application context
        registerReceiver();

        locationProvider = new ReactiveLocationProvider(appContext);

        locationRequest = LocationRequest.create() //standard GMS LocationRequest
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(TIME_GPS_UPDATE);                        //update after every 10s
//                        .setSmallestDisplacement(MIN_DISTANCE_MOVING);          // 10 meters

    }

    private void registerReceiver() {
        appContext.registerReceiver(this, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));      //must register in this to maintain activity + context
    }

    public void unregisterReceiver() {
        stopUpdateLocation();
        appContext.unregisterReceiver(this);      //must register in this to maintain activity + context
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    @SuppressLint("MissingPermission")
    public void requestLocationLastKnow() {
        this.currentType = TYPE_REQUEST_LAST_KNOW;

        if (checkGPSEnable(true)) {
            if (checkPermissionLocation(appContext)) {
                listener.permission(true);
                //has permission
                disposable = locationProvider.getLastKnownLocation()
//                        .switchIfEmpty(locationProvider.getUpdatedLocation(locationRequest))
                        .switchIfEmpty(new Observable<Location>() {
                            @Override
                            protected void subscribeActual(Observer observer) {
                                /*FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext);
                                fusedLocationClient.getLastLocation()
                                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                                            @Override
                                            public void onSuccess(Location location) {
                                                if (location == null) {
                                                    //try again
                                                    Log.d("GPS Tracker", "try again");
                                                    requestLocationLastKnowDelay();
                                                } else {
                                                    Log.d("GPS Tracker", "last know location 1");
                                                    listener.location(location);
                                                    GPSReceiver.this.lastLocation = location;
                                                }
                                            }
                                        });*/
                                stopLastKnowLocationRequest();
                                disposable2 = locationProvider.getUpdatedLocation(locationRequest)
                                        .subscribe(new Consumer<Location>() {
                                            @Override
                                            public void accept(Location location) {
                                                listener.location(location);
                                                GPSReceiver.this.lastLocation = location;
                                                Log.d("GPS Tracker", "update new location 1");
                                            }
                                        }, new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) {
                                                Logger.d(throwable);
                                            }
                                        });
                            }
                        })
                        .subscribe(new Consumer<Location>() {
                            @Override
                            public void accept(Location location) {
                                Log.d("GPS Tracker", "last know location 2");
                                listener.location(location);
                                GPSReceiver.this.lastLocation = location;
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Logger.d(throwable);
                            }
                        });
            } else {
                //has no permission
                listener.permission(false);
                stopUpdateLocation();
            }
        } else {
            stopUpdateLocation();
        }
    }

    @SuppressLint("MissingPermission")
    public void requestLocationUpdate() {
        this.currentType = TYPE_REQUEST_UPDATE;

        if (checkGPSEnable(true)) {
            if (checkPermissionLocation(appContext)) {
                listener.permission(true);
                //has permission
                Log.d("GPS Tracker", "request location update");
                disposableUpdated = locationProvider.getUpdatedLocation(locationRequest)
                        .subscribe(new Consumer<Location>() {
                            @Override
                            public void accept(Location location) {
                                listener.location(location);
                                GPSReceiver.this.lastLocation = location;
                                Log.d("GPS Tracker", "update new location");
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Logger.d(throwable.getMessage());
                            }
                        });
            } else {
                //has no permission
                listener.permission(false);
                stopUpdateLocation();
            }
        } else {
            stopUpdateLocation();
        }
    }

    public void stopLastKnowLocationRequest() {
        if (disposable2 != null) {
            disposable2.dispose();
            disposable2 = null;
        }
    }

    public void stopUpdateLocation() {
        if (disposableUpdated != null) {
            disposableUpdated.dispose();
            disposableUpdated = null;
        }

        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    public boolean checkGPSEnable(boolean isSilent) {
        LocationManager locationManager = (LocationManager) appContext.getSystemService(LOCATION_SERVICE);

        // Getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            if (!isSilent) {
                showSettingsAlert(context);
            }
            listener.gpsEnable(false);
            return false;
        }
        listener.gpsEnable(true);
        return true;
    }

    @Override
    public void onReceive(Context context, Intent intent) {     //disable for mockup gps test
        if (intent != null && intent.getAction() != null && intent.getAction().matches(LocationManager.PROVIDERS_CHANGED_ACTION)) {
            if (context != null && listener != null) {
                boolean isEnable = checkGPSEnable(true);
                if (isFirstReceive && isEnable) {
                    isFirstReceive = false;

                    EventBus.getDefault().post(EventBusKey.GPS_ON_OFF.getValue());
                    /*switch (currentType) {
                        case TYPE_REQUEST_LAST_KNOW:
                            requestLocationLastKnow();
                            break;
                        case TYPE_REQUEST_UPDATE:
                            requestLocationUpdate();
                            break;
                    }*/
                } else {
                    isFirstReceive = true;
                }
            }
        }
    }

    public static void showSettingsAlert(final Context context) {
        if (!((Activity) context).isFinishing()) {
            DialogUtils.showDialog(context, R.string.permission_location_title, R.string.permission_location_dialog_location_content,
                    R.string.permission_settings, R.string.permission_cancel,
                    new DialogUtils.DialogListener() {
                        @Override
                        public void positiveClick() {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            ((Activity) context).startActivityForResult(intent, Constant.REQUEST_CODE_RESULT_LOCATION_ON_OFF);
                        }

                        @Override
                        public void negativeClick() {
                        }
                    });
        }
    }

    private boolean checkPermissionLocation(Context appContext) {
        if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public interface GPSReceiverListener {
        void gpsEnable(boolean result);

        void permission(boolean result);

        void location(Location location);
    }

    public static boolean checkGPSEnable(Context context, boolean isSilent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

        // Getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            if (!isSilent) {
                showSettingsAlert(context);
            }
            return false;
        }
        return true;
    }
}
