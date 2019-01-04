package com.danhtran.androidbaseproject.ui.activity.demo_database;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityDemoBinding;

/**
 * Created by danhtran on 2/25/2018.
 */

public class DemoActivity extends AppCompatActivity implements DemoActivityListener {

    DemoActivityPresenter activityPresenter;
    ActivityDemoBinding activityDemoBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDemoBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);

        activityPresenter = new DemoActivityPresenter(this, activityDemoBinding.swipe, this);
        activityDemoBinding.setPresenter(activityPresenter);
        activityDemoBinding.executePendingBindings();
    }
}
