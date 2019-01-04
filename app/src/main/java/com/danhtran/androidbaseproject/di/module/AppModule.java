package com.danhtran.androidbaseproject.di.module;

import android.app.Application;
import android.content.Context;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danhtran on 2/25/2018.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(@NotNull Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application getApplication() {
        return application;
    }

    @Singleton
    @Provides
    public Context getApplicationContext() {
        return application.getApplicationContext();
    }
}
