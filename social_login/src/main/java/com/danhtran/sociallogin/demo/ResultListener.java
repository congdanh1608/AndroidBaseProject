package com.danhtran.sociallogin.demo;

/**
 * Created by SilverWolf on 1/4/2019.
 */
public interface ResultListener {
    void onSuccess(String token);

    void onFailure(Object error);
}
