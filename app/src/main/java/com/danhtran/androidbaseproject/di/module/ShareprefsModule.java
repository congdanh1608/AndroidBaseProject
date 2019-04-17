package com.danhtran.androidbaseproject.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.danhtran.androidbaseproject.database.share_preferences.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danhtran on 2/27/2018.
 */
@Module
public class ShareprefsModule {
    @Singleton
    @Provides
    public SharedPrefsHelper getSharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelper(sharedPreferences);
    }

    @Singleton
    @Provides
    public SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
