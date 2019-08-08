package com.danhtran.androidbaseproject.ui.activity.main;

import android.content.Context;
import android.content.Intent;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityMainBinding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity {
    ActivityMainBinding activityMainBinding;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
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

    @Override
    public void onBackPressed() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count <= 1) {
            exitApp();
        } else {
            super.onBackPressed();
        }
    }
}
