package com.danhtran.androidbaseproject.extras.listener;

public interface ResultListener<T extends Object> {
    void onSuccess(T data);

    void onFailure(Object error);
}
