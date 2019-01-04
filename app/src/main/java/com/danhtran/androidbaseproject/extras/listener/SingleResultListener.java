package com.danhtran.androidbaseproject.extras.listener;

import java.util.List;

public interface SingleResultListener<T extends Object> {
    void onSuccess(List<T> data);

    void onFailure(Object error);
}
