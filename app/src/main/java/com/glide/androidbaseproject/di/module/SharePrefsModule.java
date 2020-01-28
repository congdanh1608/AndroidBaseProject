package com.glide.androidbaseproject.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.glide.androidbaseproject.database.share_preferences.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danhtran on 2/27/2018.
 */
@Module
public class SharePrefsModule {
    @Singleton
    @Provides
    public SharedPrefsHelper getSharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelper(sharedPreferences);
    }

    @Singleton
    @Provides
    public SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences;
        //encrypt for release version - min api 23
//        if (BuildConfig.DEBUG) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        /*} else {
            sharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );*/

        return sharedPreferences;
    }
}
