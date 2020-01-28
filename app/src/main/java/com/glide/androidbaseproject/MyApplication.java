package com.glide.androidbaseproject;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.glide.androidbaseproject.di.component.AppComponent;
import com.glide.androidbaseproject.di.component.DaggerAppComponent;
import com.glide.androidbaseproject.di.module.AppModule;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by danhtran on 2/25/2018.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication myApplication;
    private AppComponent appComponent;
    private String token;

    public MyApplication() {
        super();
        myApplication = this;
    }

    public static MyApplication instance() {
        return myApplication;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSDK();
        initData();
    }

    private void initSDK() {
//        initHawk();
//        initFacebook();
        initFont();
        initLogger();
//        initEvernoteState();
    }

    /*private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }*/

    //init fonts for app
    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Helvetica.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initEvernoteState() {
//        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
    }

    private void initHawk() {
        Hawk.init(this).build();
    }

    private void initData() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

        //language
//        String s = SharedPrefsHelper.getInstance().readString(SharePref.LANGUAGE.toString());
//        if (s != null) lang = s;
//        else lang = Locale.getDefault().getLanguage();

        //load token
//        session = SharedPrefsHelper.getInstance().readObject(Session.class, SharePref.SESSION_LOGIN.toString());

        //secret key
//        Utils.generalSHAKey(this);
    }
}
