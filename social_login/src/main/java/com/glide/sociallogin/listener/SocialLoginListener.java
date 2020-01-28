package com.glide.sociallogin.listener;

import com.glide.sociallogin.networks.SocialNetwork;

/**
 * Created by danhtran on 05/04/2017.
 */
public interface SocialLoginListener {
    void onSuccess(SocialNetwork socialNetwork);

    void onFailure(SocialNetwork socialNetwork, Object error);
}
