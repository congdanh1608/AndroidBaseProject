package com.congdanh.androidbaseproject.view.activity.demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.database.dao.UserDAO;
import com.congdanh.androidbaseproject.database.repository.UserRepository;
import com.congdanh.androidbaseproject.databinding.DemoActivityBinding;
import com.congdanh.androidbaseproject.di.module.RoomModule;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by congd on 2/25/2018.
 */

public class DemoActivity extends AppCompatActivity implements DemoListener {
    @Inject
    UserRepository userRepository;
    @Inject
    CompositeDisposable compositeDisposable;

    DemoActivityPresenter activityPresenter;
    DemoActivityBinding demoActivityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.get(this).getAppComponent().plusRoomComponent(new RoomModule()).inject(this);

        demoActivityBinding = DataBindingUtil.setContentView(this, R.layout.demo_activity);

        activityPresenter = new DemoActivityPresenter(demoActivityBinding.swipe, this, userRepository, compositeDisposable);
        demoActivityBinding.setPresenter(activityPresenter);
        demoActivityBinding.executePendingBindings();
    }
}
