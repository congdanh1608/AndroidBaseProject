package com.congdanh.androidbaseproject.view.dialogfragment.mappick;

import android.annotation.SuppressLint;
import android.location.Location;
import android.view.View;

import com.congdanh.androidbaseproject.appmodel.MyLatLng;
import com.congdanh.androidbaseproject.receiver.LocationReceiver;
import com.congdanh.androidbaseproject.view.base.MapPresenter;
import com.congdanh.androidbaseproject.view.fragment.map.MapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.orhanobut.logger.Logger;

/**
 * Created by congdanh on 3/6/2018.
 */

public class MapPickPresenter extends MapPresenter {
    private MapPickDialogFragment dialogFragment;
    private int type;
    private LatLng currentChosen;
    private String currentAddress;
    private MapPickListener mapPickListener;


    public MapPickPresenter(MapPickDialogFragment dialogFragment, MapView mapView, int type) {
        super(mapView, dialogFragment.getContext());
        this.dialogFragment = dialogFragment;
        this.type = type;
        this.mapView = mapView;

        init();
    }

    private void init() {
        mapPickListener = dialogFragment;
        dialogFragment.showProgress();
        mapView.getMapAsync(this);
    }

    public View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValueToParent();
                dialogFragment.dismiss();
            }
        };
    }

    private void setValueToParent() {
        MapFragment mapFragment = (MapFragment) dialogFragment.getTargetFragment();
        MyLatLng myLatLng = new MyLatLng(currentChosen.latitude, currentChosen.longitude, currentAddress);
        switch (type) {
            case 0:
                mapFragment.setOrigin(myLatLng);
                break;
            case 1:
                mapFragment.setDestination(myLatLng);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        dialogFragment.hideProgress();
        LocationReceiver.instance().requestLocation(dialogFragment.getActivity(), new LocationReceiver.MyLocationCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void locationChanged(boolean success, Location location) {
                if (success) {
                    MapPickPresenter.this.googleMap.setMyLocationEnabled(true);
                    MapPickPresenter.this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                    moveToCurrentLocation(location);
                }
            }
        });

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                getAddress();
            }
        });
    }

    private void getAddress() {
        currentChosen = getCenterLocation();
        currentAddress = getCompleteAddressString(currentChosen.latitude, currentChosen.longitude);

        //set to textview
        mapPickListener.updateTextView(currentAddress);
    }
}
