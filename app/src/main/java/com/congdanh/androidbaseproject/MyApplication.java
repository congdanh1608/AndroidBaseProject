package com.congdanh.androidbaseproject;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.congdanh.androidbaseproject.di.component.ActivityComponentBuilder;
import com.congdanh.androidbaseproject.di.component.AppComponent;
import com.congdanh.androidbaseproject.di.component.DaggerAppComponent;
import com.congdanh.androidbaseproject.di.component.HasActivitySubcomponentBuilders;
import com.congdanh.androidbaseproject.di.module.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


/**
 * Created by congdanh on 2/25/2018.
 */

public class MyApplication extends MultiDexApplication implements HasActivitySubcomponentBuilders {
    @Inject
    Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> activityComponentBuilders;

    private static MyApplication myApplication;
    private AppComponent appComponent;
    private String token;
    private int versionOS;

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

    public static HasActivitySubcomponentBuilders get(Context context) {
        return ((HasActivitySubcomponentBuilders) context.getApplicationContext());
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

    public int getVersionOS() {
        return versionOS;
    }

    public void setVersionOS(int versionOS) {
        this.versionOS = versionOS;
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
    }

    /*private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }*/

    //init fonts for app
    /*private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FontUtil.ROBOTO_REGULAR)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }*/

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

    private void initData() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);

        //version OS
        versionOS = Build.VERSION.SDK_INT;

        /*//language
        String s = SharedPrefsHelper.getInstance().readString(SharePref.LANGUAGE.toString());
        if (s != null) lang = s;
        else lang = Locale.getDefault().getLanguage();

        //load token
        session = SharedPrefsHelper.getInstance().readObject(Session.class, SharePref.SESSION_LOGIN.toString());

        //secret key
        if (BuildConfig.DEBUG)
            generalSecretKey();*/
    }

    @Override
    public ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> activityClass) {
        return activityComponentBuilders.get(activityClass).get();
    }
}
