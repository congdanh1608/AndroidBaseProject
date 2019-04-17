package com.danhtran.androidbaseproject.ui.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityMainBinding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity {
    ActivityMainBinding activityMainBinding;

    public static Intent instance(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        activityMainBinding = (ActivityMainBinding) binding;
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
        activityMainBinding.setPresenter(mainActivityPresenter);
        activityMainBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
