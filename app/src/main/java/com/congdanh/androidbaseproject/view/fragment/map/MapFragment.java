package com.congdanh.androidbaseproject.view.fragment.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.appmodel.MyLatLng;
import com.congdanh.androidbaseproject.databinding.MapFragmentBinding;
import com.congdanh.androidbaseproject.view.fragment.BaseFragment;

/**
 * Created by congdanh on 3/6/2018.
 */

public class MapFragment extends BaseFragment {
    private MapFragmentBinding mapFragmentBinding;
    MapFragmentPresenter mapPresenter;

    public static MapFragment instance() {
        MapFragment mapFragment = new MapFragment();
        return mapFragment;
    }

    @Override
    public int setLayout() {
        return R.layout.map_fragment;
    }

    @Override
    public View setProgressLayout() {
        return null;
    }

    @Override
    public void initUI() {
        mapFragmentBinding = (MapFragmentBinding) binding;
        mapPresenter = new MapFragmentPresenter(this, mapFragmentBinding.mapView);

        mapFragmentBinding.setPresenter(mapPresenter);
        mapFragmentBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onConfigurationChanged() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mapFragmentBinding.mapView.onCreate(savedInstanceState);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapFragmentBinding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapFragmentBinding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapFragmentBinding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapFragmentBinding.mapView.onLowMemory();
    }

    public void setOrigin(MyLatLng origin) {
        mapPresenter.setOrigin(origin);
    }

    public void setDestination(MyLatLng destination) {
        mapPresenter.setDestination(destination);
    }
}
