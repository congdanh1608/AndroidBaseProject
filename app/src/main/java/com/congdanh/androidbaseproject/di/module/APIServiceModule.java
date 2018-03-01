package com.congdanh.androidbaseproject.di.module;

import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.congdanh.androidbaseproject.serviceAPI.apiservice.MovieService;

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
    MovieService getMovieService(APIServer apiServer) {
        return new MovieService(apiServer);
    }
}
