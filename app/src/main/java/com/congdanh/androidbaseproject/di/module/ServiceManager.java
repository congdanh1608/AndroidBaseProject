package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.congdanh.androidbaseproject.serviceAPI.apiservice.MovieManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by congdanh on 2/27/2018.
 */
@Module
public class ServiceManager {
    @Provides
    @Singleton
    MovieManager getMovieManager(APIServer apiServer) {
        return new MovieManager(apiServer);
    }
}
