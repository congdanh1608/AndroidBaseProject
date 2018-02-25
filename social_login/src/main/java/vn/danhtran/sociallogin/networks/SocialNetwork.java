package vn.danhtran.sociallogin.networks;

import android.content.Intent;

import vn.danhtran.sociallogin.MyAccessToken;
import vn.danhtran.sociallogin.listener.SocialLoginListener;

/**
 * Created by SilverWolf on 04/04/2017.
 */
public abstract class SocialNetwork {
    public static final String SHARED_PREFS_NAME = "vn_danhtran_sociallogin";
    public enum Network {
        FACEBOOK, GOOGLE_PLUS
    }

    protected SocialLoginListener socialLoginListener;

    public abstract boolean isConnected();

    public abstract void requestLogin(SocialLoginListener socialLoginListener);

    public void setSocialLoginListener(SocialLoginListener socialLoginListener) {
        this.socialLoginListener = socialLoginListener;
    }

    public abstract void logout();

    public abstract Network getNetwork();

    public abstract MyAccessToken geAccessToken();

    public abstract void onActivityResult(int requestCode, int resultCode, Intent data);
}
