package com.danhtran.androidbaseproject.ui.activity.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.database.share_preferences.SharedPrefsHelper;
import com.danhtran.androidbaseproject.extras.enums.SharePrefs;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.danhtran.androidbaseproject.ui.activity.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class SplashActivity extends BaseAppCompatActivity {
    private final int DELAY_TIME = 1000;

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    private Handler handler;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MyApplication.instance().getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayout() {
        return 0;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        handler = new Handler();
        handler.postDelayed(() -> {
            if (!sharedPrefsHelper.readBoolean(SharePrefs.IS_NOT_FIRST_VIEW.getValue())) {
                startActivityAsRoot(MainActivity.class.getName(), null);
            } else {
                startActivityAsRoot(MainActivity.class.getName(), null);
            }
        }, DELAY_TIME);
    }

    @Override
    public void initListener() {

    }
}
