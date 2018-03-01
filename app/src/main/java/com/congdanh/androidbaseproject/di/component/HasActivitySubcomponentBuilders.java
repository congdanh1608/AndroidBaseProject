package com.congdanh.androidbaseproject.di.component;

import android.app.Activity;

/**
 * Created by froger_mcs on 18/09/16.
 */
public interface HasActivitySubcomponentBuilders {
    ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> activityClass);
}