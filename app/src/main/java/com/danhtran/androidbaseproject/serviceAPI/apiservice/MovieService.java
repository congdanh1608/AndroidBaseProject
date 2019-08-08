package com.danhtran.androidbaseproject.serviceAPI.apiservice;

import com.danhtran.androidbaseproject.appmodel.Movie;
import com.danhtran.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.danhtran.androidbaseproject.serviceAPI.extras.RxScheduler;
import com.danhtran.androidbaseproject.serviceAPI.model.ResponseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by danhtran on 2/20/2018.
 */

public class MovieService {
    private APIServer apiServer;

    public MovieService(APIServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<ResponseModel<List<Movie>>> getUsersRepositories() {
        return apiServer.getMovies()
                .compose(RxScheduler.<ResponseModel<List<Movie>>>applyIoSchedulers());
    }
}
