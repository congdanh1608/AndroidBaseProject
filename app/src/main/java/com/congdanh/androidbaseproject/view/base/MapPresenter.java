package com.congdanh.androidbaseproject.view.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by congdanh on 3/6/2018.
 */

public abstract class MapPresenter extends BaseObservable implements OnMapReadyCallback {
    protected static final float DEFAULT_ZOOM = 15; //for street view.
    protected GoogleMap googleMap;
    protected MapView mapView;
    private Context context;

    public MapPresenter(MapView mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    protected void moveToCurrentLocation(Location location) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(),
                        location.getLongitude()), DEFAULT_ZOOM));
    }

    protected LatLng getCenterLocation() {
        if (googleMap != null) {
            return googleMap.getCameraPosition().target;
        }
        return null;
    }

    protected String getCompleteAddressString(double latitude, double longitude) {
        String strAdd = "";
        Geocoder geocoder;
        try {
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (!addresses.isEmpty()) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                strAdd = address;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return strAdd;
    }
}
