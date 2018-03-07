package com.congdanh.androidbaseproject.di.component;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.di.module.ActivityBindingModule;
import com.congdanh.androidbaseproject.di.module.AppModule;
import com.congdanh.androidbaseproject.di.module.NetworkModule;
import com.congdanh.androidbaseproject.di.module.RoomModule;
import com.congdanh.androidbaseproject.di.module.APIServiceModule;
import com.congdanh.androidbaseproject.di.module.ShareprefsModule;
import com.congdanh.androidbaseproject.view.fragment.map.MapFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by congdanh on 2/25/2018.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                APIServiceModule.class,
                ShareprefsModule.class,
                ActivityBindingModule.class
        }
)
public interface AppComponent {
    //inject where you want to inject to get providers.
    void inject(MyApplication myApplication);
    void inject(MapFragmentPresenter mapFragmentPresenter);

    //for subComponent
    RoomComponent plusRoomComponent(RoomModule roomModule);
}
