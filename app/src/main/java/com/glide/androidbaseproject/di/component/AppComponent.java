package com.glide.androidbaseproject.di.component;

import com.glide.androidbaseproject.MyApplication;
import com.glide.androidbaseproject.di.module.APIServiceModule;
import com.glide.androidbaseproject.di.module.AppModule;
import com.glide.androidbaseproject.di.module.NetworkModule;
import com.glide.androidbaseproject.di.module.RoomModule;
import com.glide.androidbaseproject.di.module.SharePrefsModule;
import com.glide.androidbaseproject.ui.activity.splash.SplashActivity;
import com.glide.androidbaseproject.ui.activity.tour.TourActivityPresenter;

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
                SharePrefsModule.class,
        }
)
public interface AppComponent {
    //inject where you want to inject to get providers.
    void inject(MyApplication myApplication);
    void inject(SplashActivity splashActivity);
    void inject(TourActivityPresenter tourActivityPresenter);

    //for subComponent
    RoomComponent plusRoomComponent(RoomModule roomModule);
}
