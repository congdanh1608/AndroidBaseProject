package com.danhtran.androidbaseproject.di.component;

import com.danhtran.androidbaseproject.di.module.RoomModule;
import com.danhtran.androidbaseproject.di.scope.RoomScope;
import com.danhtran.androidbaseproject.ui.activity.demo_database.DemoActivityPresenter;

import dagger.Subcomponent;

/**
 * Created by danhtran on 2/25/2018.
 */
@RoomScope
@Subcomponent(modules = RoomModule.class)
public interface RoomComponent {
    void inject(DemoActivityPresenter demoActivityPresenter);
}
