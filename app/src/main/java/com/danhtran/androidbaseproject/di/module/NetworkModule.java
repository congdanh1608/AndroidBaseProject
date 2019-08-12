package com.danhtran.androidbaseproject.di.module;

import android.content.Context;

import com.danhtran.androidbaseproject.BuildConfig;
import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.extras.enums.Header;
import com.danhtran.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    //default Retrofit with cached
    @Singleton
    @Provides
    public Retrofit getRetrofitNonCached(@Named("non_cached") OkHttpClient okHttpClient, Context context) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.domainAPI))
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())          //for null key in response
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Named("cached")
    @Provides
    public Retrofit getRetrofitWithCached(@Named("cached") OkHttpClient okHttpClient, Context context) {
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.domainAPI))
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())          //for null key in response
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Named("non_headers")
    @Provides
    public Retrofit getRetrofitNonHeaders(@Named("non_headers") OkHttpClient okHttpClient, Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .disableHtmlEscaping().create();
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.domainAPI))
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())          //for null key in response
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader(Header.HeaderValue.AUTHORIZATION.toString(),
                                        Header.TypeHeader.BEARER.toString() + MyApplication.instance().getToken())
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
                                        Header.TypeHeader.BEARER.toString() + MyApplication.instance().getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
    }

    @Singleton
    @Named("non_headers")
    @Provides
    public OkHttpClient getOkHttpClientNonHeaders(HttpLogInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        return chain.proceed(builder.build());
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    Cache provideCache(MyApplication myApplication) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB for cache
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
