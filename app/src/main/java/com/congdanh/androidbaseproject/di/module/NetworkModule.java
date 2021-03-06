package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.enums.Header;
import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIConfig;
import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;
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
 * Created by congdanh on 2/27/2018.
 */
@Module
public class NetworkModule {
    private static final int REQUEST_TIMEOUT = 60, READ_TIMEOUT = 60;

    @Provides
    @Singleton
    @Named("server")
    public APIServer getAPIServer(@Named("server") Retrofit retrofit) {
        return retrofit.create(APIServer.class);
    }

    @Provides
    @Singleton
    @Named("map")
    public APIServer getAPIServerMap(@Named("map") Retrofit retrofit) {
        return retrofit.create(APIServer.class);
    }

    @Singleton
    @Named("server")
    @Provides
    public Retrofit getRetrofit(@Named("non_cached") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.domainAPI)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Named("map")
    @Provides
    public Retrofit getRetrofitMAP(@Named("non_cached") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(APIConfig.domainMap)
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
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(myApplication.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    public HttpLogInterceptor getInterceptor() {
        return new HttpLogInterceptor(new ILogger() {
            @Override
            public void log(String msg) {
                Logger.d(msg);
            }
        });
    }

}
