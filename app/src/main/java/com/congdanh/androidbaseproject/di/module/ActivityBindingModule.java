package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.di.component.ActivityComponentBuilder;
import com.congdanh.androidbaseproject.di.scope.ActivityKey;
import com.congdanh.androidbaseproject.view.activity.demo_dagger.DaggerActivity;
import com.congdanh.androidbaseproject.view.activity.demo_dagger.DaggerActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by froger_mcs on 14/09/16.
 */

@Module(
        subcomponents = {
                DaggerActivityComponent.class
        })
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(DaggerActivity.class)
    public abstract ActivityComponentBuilder mainActivityComponentBuilder(DaggerActivityComponent.Builder impl);
}