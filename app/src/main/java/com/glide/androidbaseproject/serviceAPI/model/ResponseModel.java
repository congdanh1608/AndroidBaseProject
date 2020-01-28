package com.glide.androidbaseproject.serviceAPI.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DanhTran on 5/31/2019.
 */
public class ResponseModel<T> {
    @SerializedName("data")
    @Expose
    private T data;
    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
