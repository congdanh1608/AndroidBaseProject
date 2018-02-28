package com.congdanh.androidbaseproject.di.component;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.di.module.AppModule;
import com.congdanh.androidbaseproject.di.module.NetworkModule;
import com.congdanh.androidbaseproject.di.module.RoomModule;
import com.congdanh.androidbaseproject.di.module.ServiceManager;
import com.congdanh.androidbaseproject.di.module.ShareprefsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by congdanh on 2/25/2018.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ServiceManager.class, ShareprefsModule.class})
public interface AppComponent {
    //inject where you want to inject to get providers.
    void inject(MyApplication myApplication);

    //for subComponent
    RoomComponent plusRoomComponent(RoomModule roomModule);
}
