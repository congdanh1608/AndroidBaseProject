package com.glide.androidbaseproject.di.module;

import com.glide.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.glide.androidbaseproject.serviceAPI.apiservice.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by danhtran on 2/27/2018.
 */
@Module
public class APIServiceModule {
    @Provides
    @Singleton
    MovieService getMovieService(APIServer apiServer) {
        return new MovieService(apiServer);
    }
}
