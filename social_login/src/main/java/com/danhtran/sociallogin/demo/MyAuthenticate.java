package com.danhtran.sociallogin.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.danhtran.sociallogin.MyAccessToken;
import com.danhtran.sociallogin.SocialLogin;
import com.danhtran.sociallogin.listener.SocialLoginListener;
import com.danhtran.sociallogin.networks.FacebookNetwork;
import com.danhtran.sociallogin.networks.GoogleNetwork;
import com.danhtran.sociallogin.networks.SocialNetwork;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by danhtran on 13/02/2017.
 * Here is example how to use this library
 */
public class MyAuthenticate implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final static int TYPE_LOGIN_GOOGLE = 0;
    public final static int TYPE_LOGIN_FACEBOOK = 1;
    public final static int TYPE_LOGIN_OTHER = 3;

    private FragmentActivity activity;
    private SocialLogin socialLogin;
    private ResultListener resultListener;

    private static volatile MyAuthenticate myAuthenticate;

    public static MyAuthenticate getInstance() {
        if (myAuthenticate == null) {
            synchronized (MyAuthenticate.class) {
                if (myAuthenticate == null) {
                    myAuthenticate = new MyAuthenticate();
                    SocialLogin.initialize();
                }
            }
        }

        return myAuthenticate;
    }


    private void initData(ResultListener resultListener) {
        this.resultListener = resultListener;
        socialLogin = SocialLogin.getInstance();
    }

    //call to login with facebook / google
    public void loginWith(int typeLogin, FragmentActivity activity, ResultListener resultListener) {
        initData(resultListener);
        switch (typeLogin) {
            case TYPE_LOGIN_FACEBOOK:
                loginWithFacebook(activity);
                //call api login
                break;
            case TYPE_LOGIN_GOOGLE:
                loginWithGoogle(activity);
                //call api login
                break;
        }
    }

    private void loginWithFacebook(Activity activity) {
        List<String> fbScope = new ArrayList<>();
        fbScope.addAll(Collections.singletonList("public_profile"));
        FacebookNetwork facebookNetwork = new FacebookNetwork(activity, fbScope);
        socialLogin.addSocialNetwork(facebookNetwork);
        facebookNetwork.requestLogin(new SocialLoginListener() {
            @Override
            public void onSuccess(SocialNetwork socialNetwork) {
                MyAccessToken token = socialNetwork.geAccessToken();
            }

            @Override
            public void onFailure(SocialNetwork socialNetwork, Object error) {
            }
        });
    }

    private void loginWithGoogle(Activity activity) {
        GoogleNetwork googleNetwork = new GoogleNetwork(activity);
        socialLogin.addSocialNetwork(googleNetwork);
        googleNetwork.requestLogin(new SocialLoginListener() {
            @Override
            public void onSuccess(SocialNetwork socialNetwork) {
                MyAccessToken token = socialNetwork.geAccessToken();
            }

            @Override
            public void onFailure(SocialNetwork socialNetwork, Object error) {

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        socialLogin.onActivityResult(requestCode, resultCode, data);
    }

    /*public void isAuthenticated(FragmentActivity activity, SocialLoginListener<String> socialLoginListener) {
        initData(activity, socialLoginListener);
        List<String> includes = new ArrayList<>();
        includes.add(MyConstants.TYPE_INCLUDE.user.name());
        APIManager.instance().auth().isAuthenticated(includes, new SocialLoginListener<AccessTokenDataResponse>() {
            @Override
            public void onSuccess(List<AccessTokenDataResponse> data) {
                saveServerToken(null);
            }

            @Override
            public void onFailure(Object error) {
                saveServerError(error);
            }
        });
    }

    public void loginWithPhone(@NotNull String phone, @NotNull String password, FragmentActivity activity, SocialLoginListener<String> socialLoginListener) {
        initData(activity, socialLoginListener);
        List<String> includes = new ArrayList<>();
        includes.add(MyConstants.TYPE_INCLUDE.user.name());
        APIManager.instance().auth().login(phone, password, includes, new SocialLoginListener<AccessTokenDataResponse>() {
            @Override
            public void onSuccess(List<AccessTokenDataResponse> data) {
                if (data.get(0).getData() != null)
                    saveServerToken(data.get(0).getData().getAccessToken());
            }

            @Override
            public void onFailure(Object error) {
                saveServerError(error);
            }
        });
    }

    public void signUpWithPhone(@NotNull final String phone, @NotNull final String password, @NotNull String firstName, @NotNull String lastName,
                                final FragmentActivity activity, final SocialLoginListener<String> socialLoginListener) {
        initData(activity, socialLoginListener);

        APIManager.instance().auth().signUp(phone, password, firstName, lastName, new SocialLoginListener<IsSuccessModel>() {
            @Override
            public void onSuccess(List<IsSuccessModel> data) {
                loginWithPhone(phone, password, activity, socialLoginListener);
            }

            @Override
            public void onFailure(Object error) {
                saveServerError(error);
            }
        });
    }

    public void forgetPassword(@NotNull String email, FragmentActivity activity, SocialLoginListener<String> socialLoginListener) {
        initData(activity, socialLoginListener);
        APIManager.instance().auth().resetPassword(email, new SocialLoginListener<IsSuccessModel>() {
            @Override
            public void onSuccess(List<IsSuccessModel> data) {
                saveServerToken(null);
            }

            @Override
            public void onFailure(Object error) {
                saveServerError(error);
            }
        });
    }*/

    public void logout(ResultListener singleResultListener) {
        initData(singleResultListener);
        SocialNetwork facebookNetwork = socialLogin.getSocialNetwork(SocialNetwork.Network.FACEBOOK);
        SocialNetwork googleNetwork = socialLogin.getSocialNetwork(SocialNetwork.Network.GOOGLE_PLUS);
        if (facebookNetwork != null)
            facebookNetwork.logout();
        if (googleNetwork != null)
            googleNetwork.logout();
        singleResultListener.onSuccess(null);           //temp - use it for logout from server
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void saveServerToken(String token) {
        resultListener.onSuccess(token);
    }

    private void saveServerError(Object error) {
        resultListener.onFailure(error);
    }
}
