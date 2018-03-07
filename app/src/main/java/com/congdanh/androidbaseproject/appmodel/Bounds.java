package com.congdanh.androidbaseproject.appmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by congdanh on 3/5/2018.
 */

public class Bounds {
    @SerializedName("northeast")
    @Expose
    private MyLatLng northeast;
    @SerializedName("southwest")
    @Expose
    private MyLatLng southwest;

    public MyLatLng getNortheast() {
        return northeast;
    }

    public void setNortheast(MyLatLng northeast) {
        this.northeast = northeast;
    }

    public MyLatLng getSouthwest() {
        return southwest;
    }

    public void setSouthwest(MyLatLng southwest) {
        this.southwest = southwest;
    }
}
