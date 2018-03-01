package com.congdanh.androidbaseproject.view.activity.demo_dagger;

import android.view.View;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.databinding.DaggerActivityBinding;
import com.congdanh.androidbaseproject.di.component.HasActivitySubcomponentBuilders;
import com.congdanh.androidbaseproject.view.activity.BaseAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by congdanh on 2/28/2018.
 */

public class DaggerActivity extends BaseAppCompatActivity implements DaggerListener {
    DaggerActivityBinding daggerActivityBinding;

    @Inject
    DaggerPresenter daggerPresenter;

    @Override
    public int setLayout() {
        return R.layout.dagger_activity;
    }

    @Override
    public View setProgressLayout() {
        return null;
    }

    @Override
    protected void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders) {
        ((DaggerActivityComponent.Builder) hasActivitySubcomponentBuilders.getActivityComponentBuilder(DaggerActivity.class))
                .activityModule(new DaggerActivityComponent.DaggerActivityModule(this, this))
                .build()
                .injectMembers(this);
    }

    @Override
    public void initUI() {
        daggerActivityBinding = (DaggerActivityBinding) binding;

        daggerActivityBinding.setPresenter(daggerPresenter);
        daggerActivityBinding.executePendingBindings();
    }

    @Override
    public void initData() {
        daggerPresenter.initialize("Demo string");
    }

    @Override
    public void initListener() {

    }
}
