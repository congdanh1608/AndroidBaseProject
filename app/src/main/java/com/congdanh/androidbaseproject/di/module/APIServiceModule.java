package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.congdanh.androidbaseproject.serviceAPI.apiservice.MapService;
import com.congdanh.androidbaseproject.serviceAPI.apiservice.MovieService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by congdanh on 2/27/2018.
 */
@Module
public class APIServiceModule {
    @Provides
    @Singleton
    MovieService getMovieService(@Named("server") APIServer apiServer) {
        return new MovieService(apiServer);
    }

    @Provides
    @Singleton
    MapService getMapService(@Named("map") APIServer apiServer) {
        return new MapService(apiServer);
    }
}
