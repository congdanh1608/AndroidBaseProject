package com.danhtran.androidbaseproject.ui.activity.secondary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivitySecondaryBinding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class SecondaryActivity extends BaseAppCompatActivity implements SecondaryActivityListener {
    public static final String KEY_FRAGMENT_TAG = "KEY_FRAGMENT_TAG";
    public static final String KEY_FRAGMENT_BUNDLE = "KEY_FRAGMENT_BUNDLE";

    private ActivitySecondaryBinding mBinding;
    private SecondaryActivityPresenter presenter;

    private String fragmentTag;
    private Object bundle;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SecondaryActivity.class);
        return intent;
    }

    @Override
    protected void loadPassedParamsIfNeeded(@NonNull Bundle extras) {
        super.loadPassedParamsIfNeeded(extras);

        fragmentTag = extras.getString(KEY_FRAGMENT_TAG);
        bundle = extras.get(KEY_FRAGMENT_BUNDLE);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_secondary;
    }

    @Override
    public void initUI() {
        mBinding = (ActivitySecondaryBinding) binding;
    }

    @Override
    public void initData() {
        presenter = new SecondaryActivityPresenter(this, bundle);
        mBinding.setPresenter(presenter);
        mBinding.executePendingBindings();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public String getFragmentTag() {
        return fragmentTag;
    }
}
