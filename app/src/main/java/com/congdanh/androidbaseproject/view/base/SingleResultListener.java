package com.congdanh.androidbaseproject.view.base;

import java.util.List;

/**
 * Created by vitcon on 4/23/16.
 */
public interface SingleResultListener<T extends Object> {
    void onSuccess(List<T> data);

    void onFailure(Object error);


}
