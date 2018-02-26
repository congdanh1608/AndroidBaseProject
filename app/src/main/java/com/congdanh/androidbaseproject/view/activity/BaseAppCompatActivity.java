package com.congdanh.androidbaseproject.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by congd on 2/26/2018.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private View progressBar;
    protected ViewDataBinding binding;
    protected FragmentManager mFragmentManager;

    public abstract int setLayout();

    //set handler + execute view binding
    public abstract void initUI();

    public abstract void initData();

    public abstract void initListener();

    public ViewDataBinding getBinding() {
        return binding;
    }

    public FragmentManager getmFragmentManager() {
        return mFragmentManager;
    }

    private void initFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int xml = setLayout();
        if (xml != 0 && binding == null) {
            binding = DataBindingUtil.setContentView(this, xml);
        }
        initFragmentManager();
        initUI();
        initListener();
        initData();
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
