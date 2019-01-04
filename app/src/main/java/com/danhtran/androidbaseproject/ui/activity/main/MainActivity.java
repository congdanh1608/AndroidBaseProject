package com.danhtran.androidbaseproject.ui.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    public static Intent instance(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setPresenter(mainActivityPresenter);
        activityMainBinding.executePendingBindings();
    }
}
