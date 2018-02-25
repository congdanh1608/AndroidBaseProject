package com.congdanh.androidbaseproject.di.component;

import com.congdanh.androidbaseproject.di.module.RoomModule;
import com.congdanh.androidbaseproject.di.scope.RoomScope;
import com.congdanh.androidbaseproject.view.activity.demo.DemoActivity;

import dagger.Subcomponent;

/**
 * Created by congd on 2/25/2018.
 */
@RoomScope
@Subcomponent(modules = RoomModule.class)
public interface RoomComponent {
    void inject(DemoActivity demoActivity);
}
