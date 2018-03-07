package com.congdanh.androidbaseproject.view.activity.demo_map;

import android.view.View;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.databinding.MapActivityBinding;
import com.congdanh.androidbaseproject.di.component.HasActivitySubcomponentBuilders;
import com.congdanh.androidbaseproject.view.activity.BaseAppCompatActivity;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by congdanh on 2/28/2018.
 */

public class MapActivity extends BaseAppCompatActivity {
    MapActivityBinding mapActivityBinding;
    MapActivityPresenter mapActivityPresenter;

    @Override
    public int setLayout() {
        return R.layout.map_activity;
    }

    @Override
    public View setProgressLayout() {
        return null;
    }

    @Override
    protected void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders) {

    }

    @Override
    public void initUI() {
        mapActivityBinding = (MapActivityBinding) binding;

        mapActivityPresenter = new MapActivityPresenter(this);
        mapActivityBinding.setPresenter(mapActivityPresenter);
        mapActivityBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int count = getFragmentManager().getBackStackEntryCount();
        if (count <= 1) finish();
    }
}
