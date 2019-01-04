package com.danhtran.androidbaseproject;

import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.danhtran.androidbaseproject.di.component.AppComponent;
import com.danhtran.androidbaseproject.di.component.DaggerAppComponent;
import com.danhtran.androidbaseproject.di.module.AppModule;
import com.danhtran.androidbaseproject.utils.Utils;
import com.evernote.android.state.StateSaver;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by SilverWolf on 2/25/2018.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication myApplication;
    private AppComponent appComponent;
    private String token;

    public MyApplication() {
        super();
        myApplication = this;
    }

    public static MyApplication Instance() {
        return myApplication;
    }

//    public static MyApplication get(Context context) {
//        return (MyApplication) context.getApplicationContext();
//    }

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
//        initFacebook();
//        initFont();
//        initFabric();
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
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    /*private void initFabric() {
        Fabric.with(this, new Crashlytics());
    }*/

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initEvernoteState() {
        StateSaver.setEnabledForAllActivitiesAndSupportFragments(this, true);
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
