package com.glide.sociallogin.networks;

import android.app.Activity;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.lang.ref.WeakReference;
import java.util.List;

import com.glide.sociallogin.MyAccessToken;
import com.glide.sociallogin.listener.SocialLoginListener;

/**
 * Created by danhtran on 04/04/2017.
 */
public class FacebookNetwork extends SocialNetwork {
    private CallbackManager callbackManager;
    private WeakReference<Activity> activity;
    private List<String> permissions;
    private MyAccessToken myAccessToken;

    public FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            String token = loginResult.getAccessToken().getToken();
            String userId = loginResult.getAccessToken().getUserId();

            myAccessToken = new MyAccessToken.Builder(token).userId(userId).build();
            socialLoginListener.onSuccess(FacebookNetwork.this);
        }

        @Override
        public void onCancel() {
            socialLoginListener.onFailure(FacebookNetwork.this, null);
        }

        @Override
        public void onError(FacebookException error) {
            socialLoginListener.onFailure(FacebookNetwork.this, error);
        }
    };

    public FacebookNetwork(Activity activity, List<String> permissions) {
        this.activity = new WeakReference<>(activity);
        callbackManager = CallbackManager.Factory.create();
        String applicationID = Utility.getMetadataApplicationId(this.activity.get());
        this.permissions = permissions;

        if (applicationID == null) {
            throw new IllegalStateException("applicationID can't be null\n" +
                    "Please check https://developers.facebook.com/docs/android/getting-started/");
        }
    }

    @Override
    public boolean isConnected() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    @Override
    public void requestLogin(SocialLoginListener socialLoginListener) {
        setSocialLoginListener(socialLoginListener);
        LoginManager.getInstance().logInWithReadPermissions(activity.get(), permissions);
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);
    }

    @Override
    public void logout() {
        LoginManager.getInstance().logOut();
    }

    @Override
    public Network getNetwork() {
        return Network.FACEBOOK;
    }

    @Override
    public MyAccessToken geAccessToken() {
        if (AccessToken.getCurrentAccessToken() != null && myAccessToken == null) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            myAccessToken = new MyAccessToken.Builder(accessToken.getToken()).userId(accessToken.getUserId()).build();
        }
        return myAccessToken;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
