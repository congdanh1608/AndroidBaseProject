package com.danhtran.androidbaseproject.serviceAPI.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DanhTran on 5/31/2019.
 */
public class Error {

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
