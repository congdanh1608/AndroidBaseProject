package com.danhtran.androidbaseproject.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityMainBinding;
import com.danhtran.androidbaseproject.extras.Constant;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity implements MainActivityListener {
    private ActivityMainBinding mBinding;
    private MainActivityPresenter presenter;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load deep link when app is running
        onNewIntent(getIntent());
    }

    @Override
    protected void loadPassedParamsIfNeeded(@NonNull Bundle extras) {
        super.loadPassedParamsIfNeeded(extras);
        //load deep link when app is not running

    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        mBinding = (ActivityMainBinding) binding;

    }

    @Override
    public void initData() {
        presenter = new MainActivityPresenter(this);
        mBinding.setPresenter(presenter);
        mBinding.executePendingBindings();
    }

    @Override
    public void initListener() {
        mBinding.btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Crashlytics.getInstance().crash();
                    }
                }
        );
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_CODE_RESULT_LOCATION_ON_OFF:
                break;
            case Constant.REQUEST_CODE_RESULT_LOCATION_PERMISSION:
                break;
        }
    }

    //load deep link data onNewIntent()
    protected void onNewIntent(Intent intent) {
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            String type = data.substring(data.lastIndexOf("/") + 1);
            if (!TextUtils.isEmpty(type)) {
                //do something
            }
        }
    }
}
