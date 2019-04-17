package com.danhtran.androidbaseproject.extras.listener;

public interface SingleResultListener<T extends Object> {
    void onSuccess(T data);

    void onFailure(Object error);
}
