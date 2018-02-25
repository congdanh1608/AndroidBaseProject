package com.congdanh.androidbaseproject.di.component;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.di.module.AppModule;
import com.congdanh.androidbaseproject.di.module.RoomModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by congd on 2/25/2018.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApplication myApplication);

    RoomComponent plusRoomComponent(RoomModule roomModule);
}
