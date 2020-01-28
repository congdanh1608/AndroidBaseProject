package com.glide.androidbaseproject.di.component;

import com.glide.androidbaseproject.di.module.RoomModule;
import com.glide.androidbaseproject.di.scope.RoomScope;

import dagger.Subcomponent;

/**
 * Created by danhtran on 2/25/2018.
 */
@RoomScope
@Subcomponent(modules = RoomModule.class)
public interface RoomComponent {

//    void inject(DemoActivityPresenter demoActivityPresenter);
}
