package com.congdanh.androidbaseproject.view.activity.demo_dagger;

import com.congdanh.androidbaseproject.di.component.ActivityComponent;
import com.congdanh.androidbaseproject.di.component.ActivityComponentBuilder;
import com.congdanh.androidbaseproject.di.module.ActivityModule;
import com.congdanh.androidbaseproject.di.scope.ActivityScope;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by froger_mcs on 14/09/16.
 */

@ActivityScope
@Subcomponent(
        modules = DaggerActivityComponent.DaggerActivityModule.class
)
public interface DaggerActivityComponent extends ActivityComponent<DaggerActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<DaggerActivityModule, DaggerActivityComponent> {
    }

    @Module
    class DaggerActivityModule extends ActivityModule<DaggerActivity, DaggerListener> {

        public DaggerActivityModule(DaggerActivity activity, DaggerListener listener) {
            super(activity, listener);
        }
    }
}
