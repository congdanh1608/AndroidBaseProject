package com.danhtran.androidbaseproject.view.activity.demo_database;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.DemoActivityBinding;

/**
 * Created by SilverWolf on 2/25/2018.
 */

public class DemoActivity extends AppCompatActivity implements DemoActivityListener {

    DemoActivityPresenter activityPresenter;
    DemoActivityBinding demoActivityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        demoActivityBinding = DataBindingUtil.setContentView(this, R.layout.demo_activity);

        activityPresenter = new DemoActivityPresenter(this, demoActivityBinding.swipe, this);
        demoActivityBinding.setPresenter(activityPresenter);
        demoActivityBinding.executePendingBindings();
    }
}
