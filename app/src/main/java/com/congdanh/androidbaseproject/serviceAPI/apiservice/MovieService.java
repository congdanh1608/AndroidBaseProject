package com.congdanh.androidbaseproject.serviceAPI.apiservice;

import com.congdanh.androidbaseproject.appmodel.Movie;
import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congd on 2/20/2018.
 */

public class MovieService {
    private APIServer apiServer;

    public MovieService(APIServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<List<Movie>> getUsersRepositories() {
        return apiServer.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
