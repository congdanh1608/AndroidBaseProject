package com.danhtran.androidbaseproject.di.component;

import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.di.module.APIServiceModule;
import com.danhtran.androidbaseproject.di.module.AppModule;
import com.danhtran.androidbaseproject.di.module.NetworkModule;
import com.danhtran.androidbaseproject.di.module.RoomModule;
import com.danhtran.androidbaseproject.di.module.ShareprefsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by danhtran on 2/25/2018.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                APIServiceModule.class,
                ShareprefsModule.class,
        }
)
public interface AppComponent {
    //inject where you want to inject to get providers.
    void inject(MyApplication myApplication);

    //for subComponent
    RoomComponent plusRoomComponent(RoomModule roomModule);
}
