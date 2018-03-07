package com.congdanh.androidbaseproject.view.fragment.map;

import android.annotation.SuppressLint;
import android.databinding.Bindable;
import android.graphics.Color;
import android.location.Location;
import android.view.View;

import com.congdanh.androidbaseproject.BR;
import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.appmodel.MapReponse;
import com.congdanh.androidbaseproject.appmodel.MyLatLng;
import com.congdanh.androidbaseproject.receiver.LocationReceiver;
import com.congdanh.androidbaseproject.serviceAPI.apiservice.MapService;
import com.congdanh.androidbaseproject.utils.ViewUtils;
import com.congdanh.androidbaseproject.view.base.MapPresenter;
import com.congdanh.androidbaseproject.view.dialogfragment.mappick.MapPickDialogFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by congdanh on 3/6/2018.
 */

public class MapFragmentPresenter extends MapPresenter {
    @Inject
    MapService mapService;

    private MapFragment mapFragment;
    private MyLatLng origin, destination;

    public MapFragmentPresenter(MapFragment mapFragment, MapView mapView) {
        super(mapView, mapFragment.getContext());
        this.mapFragment = mapFragment;
        this.mapView = mapView;

        init();
    }

    private void init() {
        MyApplication.Instance().getAppComponent().inject(this);
        mapView.getMapAsync(this);
    }

    public void setOrigin(MyLatLng origin) {
        this.origin = origin;
        notifyPropertyChanged(BR.origin);
    }

    public void setDestination(MyLatLng destination) {
        this.destination = destination;
        notifyPropertyChanged(BR.destination);
    }

    @Bindable
    public MyLatLng getOrigin() {
        return origin;
    }

    @Bindable
    public MyLatLng getDestination() {
        return destination;
    }

    public View.OnClickListener onClickListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        ViewUtils.showDialogFragment(MapPickDialogFragment.instance(0), mapFragment,
                                MapPickDialogFragment.class.getName(), 0);
                        break;
                    case 1:
                        ViewUtils.showDialogFragment(MapPickDialogFragment.instance(1), mapFragment,
                                MapPickDialogFragment.class.getName(), 0);
                        break;
                    case 2:
                        getDirection();
                        break;
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
        LocationReceiver.instance().requestLocation(mapFragment.getBaseActivity(), new LocationReceiver.MyLocationCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void locationChanged(boolean success, Location location) {
                if (success) {
                    MapFragmentPresenter.this.googleMap.setMyLocationEnabled(true);
                    MapFragmentPresenter.this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                    moveToCurrentLocation(location);
                }
            }
        });
    }

    private void getDirection() {
        mapService.getMapDirection(origin.getLatLng(), destination.getLatLng(), mapFragment.getString(R.string.key_google_web))
                .subscribe(new Consumer<MapReponse>() {
                    @Override
                    public void accept(MapReponse mapReponses) throws Exception {
                        drawPolyline(mapReponses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private ArrayList<LatLng> decodePoly(String encoded) {
        ArrayList<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;
        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;
            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng position = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(position);
        }
        return poly;
    }

    private void drawPolyline(MapReponse mapReponse) {
        if (googleMap != null) {
            //draw marker
            MyLatLng startPoint = mapReponse.getRoutes().get(0).getLegs().get(0).getStartLocation();
            MyLatLng endPoint = mapReponse.getRoutes().get(0).getLegs().get(0).getEndLocation();
            googleMap.addMarker(new MarkerOptions().position(startPoint.getLatLng())
                    .title("A"));
            googleMap.addMarker(new MarkerOptions().position(endPoint.getLatLng())
                    .title("B"));

            //draw direction
            String polyCode = mapReponse.getRoutes().get(0).getOverviewPolyline().getPoints();
            PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.RED);
            List<LatLng> directionPoint = decodePoly(polyCode);
            for (int i = 0; i < directionPoint.size(); i++) {
                rectLine.add(directionPoint.get(i));
            }
            googleMap.addPolyline(rectLine);
        }
    }
}
