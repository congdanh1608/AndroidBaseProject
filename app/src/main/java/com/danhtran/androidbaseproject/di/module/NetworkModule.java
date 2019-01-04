package com.danhtran.androidbaseproject.di.module;

import android.content.Context;

import com.danhtran.androidbaseproject.BuildConfig;
import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.extras.enums.Header;
import com.danhtran.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.halcyon.logger.HttpLogInterceptor;
import com.halcyon.logger.ILogger;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danhtran on 2/27/2018.
 */
@Module
public class NetworkModule {
    private static final int REQUEST_TIMEOUT = 60, READ_TIMEOUT = 60;

    @Provides
    @Singleton
    public APIServer getAPIServer(Retrofit retrofit) {
        return retrofit.create(APIServer.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofit(@Named("non_cached") OkHttpClient okHttpClient, Context context) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.domainAPI))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Named("non_cached")
    @Provides
    public OkHttpClient getOkHttpClient(HttpLogInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                //for test
//                .addInterceptor(chain -> {
//                    Request newRequest  = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI1OGY0MTUwOGI3ZWZhNzUwMTFhMDA4YWMiLCJ1c2VyRW1haWwiOiJjaGFyYWN0ZXIyQGFuaW1lbG92ZXJzYXBwLmNvbSIsInVzZXJDcmVhdGVkRGF0ZSI6IjIwMTctMDQtMTdUMDE6MDY6MTYuMDQ3WiIsImlzQWRtaW4iOmZhbHNlLCJpYXQiOjE0OTI0MTU2MzEsImV4cCI6MTQ5NzU5OTYzMX0.BkDMHojXxBZjziDaxb72RrayFR3ZYctaKn51fn6WhHE")
//                            .build();
//                    return chain.proceed(newRequest);
//                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader(Header.HeaderValue.AUTHORIZATION.toString(),
                                        Header.TypeHeader.BEARER.toString() + MyApplication.Instance().getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Provides
    @Named("cached")
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLogInterceptor httpLogInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLogInterceptor)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader(Header.HeaderValue.AUTHORIZATION.toString(),
                                        Header.TypeHeader.BEARER.toString() + MyApplication.Instance().getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    Cache provideCache(MyApplication myApplication) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB for cache
        return new Cache(myApplication.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    public HttpLogInterceptor getInterceptor() {
        return new HttpLogInterceptor(new ILogger() {
            @Override
            public void log(String msg) {
                if (BuildConfig.DEBUG) {
                    Logger.d(msg);
                }
            }
        });
    }

}
