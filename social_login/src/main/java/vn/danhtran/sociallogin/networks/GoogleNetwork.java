package vn.danhtran.sociallogin.networks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;

import java.lang.ref.WeakReference;

import vn.danhtran.sociallogin.MyAccessToken;
import vn.danhtran.sociallogin.listener.SocialLoginListener;

/**
 * Created by congdanh on 04/04/2017.
 */
public class GoogleNetwork extends SocialNetwork implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private static final String GPLUS_CONNECTED = "google_plus_connected";
    private GoogleApiClient googleApiClient;
    private MyAccessToken accessToken;
    private SharedPreferences sharedPreferences;
    private static final int REQUEST_AUTH = 1999;
    private WeakReference<Activity> activity;

    public GoogleNetwork(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        this.activity = new WeakReference<>(activity);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestScopes(new Scope(Scopes.PLUS_ME))
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(activity);
        if (activity instanceof FragmentActivity) {
            builder.enableAutoManage((FragmentActivity) activity, this);
        } else {
            builder.addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this);
        }

        googleApiClient = builder.addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }

    public void silentSignIn() {
        OptionalPendingResult<GoogleSignInResult> pendingResult =
                Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (pendingResult.isDone()) {
            parseGoogleSignInResult(pendingResult.get());
        } else {
            pendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    parseGoogleSignInResult(result);
                }
            });
        }
    }

    private String getStatusCodeString(int statusCode) {
        return CommonStatusCodes.getStatusCodeString(statusCode);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void requestLogin(SocialLoginListener socialLoginListener) {
        setSocialLoginListener(socialLoginListener);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        activity.get().startActivityForResult(signInIntent, REQUEST_AUTH);
    }

    @Override
    public void logout() {
        if (isConnected()) {
            Auth.GoogleSignInApi.signOut(googleApiClient);
            googleApiClient.disconnect();
            sharedPreferences.edit().putBoolean(GPLUS_CONNECTED, false).apply();
        }
    }

    @Override
    public Network getNetwork() {
        return Network.GOOGLE_PLUS;
    }

    @Override
    public MyAccessToken geAccessToken() {
        return accessToken;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_AUTH) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        parseGoogleSignInResult(result);
    }

    private void parseGoogleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            sharedPreferences.edit().putBoolean(GPLUS_CONNECTED, true).apply();
            GoogleSignInAccount acct = result.getSignInAccount();
            if (acct != null) {
                accessToken = new MyAccessToken.Builder(acct.getId())
                        .email(acct.getEmail())
                        .username(acct.getDisplayName())
                        .userId(acct.getId())
                        .build();
                socialLoginListener.onSuccess(GoogleNetwork.this);
            }
        } else {
            if (result.getStatus().getStatusCode() == CommonStatusCodes.SIGN_IN_REQUIRED) {
                if (socialLoginListener != null) {
                    requestLogin(socialLoginListener);
                }
                return;
            }
            sharedPreferences.edit().putBoolean(GPLUS_CONNECTED, false).apply();
            socialLoginListener.onFailure(GoogleNetwork.this, getStatusCodeString(result.getStatus().getStatusCode()));
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
