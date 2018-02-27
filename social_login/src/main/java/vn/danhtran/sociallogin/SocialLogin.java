package vn.danhtran.sociallogin;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.danhtran.sociallogin.networks.SocialNetwork;

/**
 * Created by congdanh on 04/04/2017.
 */
public class SocialLogin {
    private static SocialLogin instance = null;
    private Map<SocialNetwork.Network, SocialNetwork> networkMap = new HashMap<>();

    private SocialLogin() {

    }

    public static void initialize() {
        if (instance == null)
            instance = new SocialLogin();
    }

    public static SocialLogin getInstance() {
        return instance;
    }

    public void addSocialNetwork(SocialNetwork socialNetwork) {
        if (networkMap.get(socialNetwork.getNetwork()) != null) {
            throw new RuntimeException("Social network with id = " + socialNetwork.getNetwork() + " already exists");
        }

        networkMap.put(socialNetwork.getNetwork(), socialNetwork);
    }

    public SocialNetwork getSocialNetwork(SocialNetwork.Network network) throws RuntimeException {
        if (!networkMap.containsKey(network)) {
            return null;
        }
        return networkMap.get(network);
    }

    public List<SocialNetwork> getInitializedSocialNetworks() {
        return Collections.unmodifiableList(new ArrayList<>(networkMap.values()));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (SocialNetwork socialNetwork : networkMap.values()) {
            socialNetwork.onActivityResult(requestCode, resultCode, data);
        }
    }
}
