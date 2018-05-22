package com.congdanh.androidbaseproject.view.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    MainActivityBinding mainActivityBinding;

    public static Intent instance(Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainActivityBinding.setPresenter(mainActivityPresenter);
        mainActivityBinding.executePendingBindings();
    }
}
