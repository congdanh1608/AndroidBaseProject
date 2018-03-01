package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by froger_mcs on 09/08/16.
 */

@Module
public abstract class ActivityModule<T, K> {
    protected final T activity;
    protected final K listener;

    public ActivityModule(T activity, K listener) {
        this.activity = activity;
        this.listener = listener;
    }

    @Provides
    @ActivityScope
    public T getActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    public K getListener() {
        return listener;
    }
}
