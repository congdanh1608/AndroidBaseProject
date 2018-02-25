package vn.danhtran.sociallogin.listener;

import vn.danhtran.sociallogin.networks.SocialNetwork;

/**
 * Created by SilverWolf on 05/04/2017.
 */
public interface SocialLoginListener {
    void onSuccess(SocialNetwork socialNetwork);

    void onFailure(SocialNetwork socialNetwork, Object error);
}
