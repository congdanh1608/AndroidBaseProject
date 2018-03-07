package com.congdanh.androidbaseproject.serviceAPI.apiservice;

import com.congdanh.androidbaseproject.appmodel.MapReponse;
import com.congdanh.androidbaseproject.enums.QueryString;
import com.congdanh.androidbaseproject.serviceAPI.apiconfig.APIServer;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by congd on 2/20/2018.
 */

public class MapService {
    private APIServer apiServer;

    public MapService(APIServer apiServer) {
        this.apiServer = apiServer;
    }

    public Observable<MapReponse> getMapDirection(String origin, String destination, String mode, String key) {
        Map<String, Object> params = new HashMap<>();
        params.put(QueryString.ORIGIN.toString(), origin);
        params.put(QueryString.DESTINATION.toString(), destination);
        params.put(QueryString.MODE.toString(), mode);
        params.put(QueryString.KEY.toString(), key);
        return apiServer.getMapDirection(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MapReponse> getMapDirection(LatLng origin, LatLng destination, String key) {
        String ori = origin.latitude + "," + origin.longitude;
        String des = destination.latitude + "," + destination.longitude;
        Map<String, Object> params = new HashMap<>();
        params.put(QueryString.ORIGIN.toString(), ori);
        params.put(QueryString.DESTINATION.toString(), des);
        params.put(QueryString.KEY.toString(), key);
        return apiServer.getMapDirection(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
