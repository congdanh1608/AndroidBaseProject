package com.congdanh.androidbaseproject.view.activity.demo_map;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.blankj.utilcode.util.FragmentUtils;
import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.receiver.LocationReceiver;
import com.congdanh.androidbaseproject.view.activity.BaseAppCompatActivity;
import com.congdanh.androidbaseproject.view.fragment.map.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Locale;

/**
 * Created by congdanh on 2/28/2018.
 */

public class MapActivityPresenter {
    private BaseAppCompatActivity mapActivity;
    private GoogleMap googleMap;
    private LatLng destination;
    private Location current;

    public MapActivityPresenter(MapActivity mapActivity) {
        this.mapActivity = mapActivity;

        init();
    }

    private void init() {
        FragmentUtils.replaceFragment(mapActivity.getmFragmentManager(), MapFragment.instance(), R.id.content_fragment, true);
    }

    public void setDestination(LatLng destination) {
        this.destination = destination;
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(destination));
        }
    }
}
