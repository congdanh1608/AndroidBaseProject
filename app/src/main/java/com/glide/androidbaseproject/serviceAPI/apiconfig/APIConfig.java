package com.glide.androidbaseproject.serviceAPI.apiconfig;

import com.glide.androidbaseproject.BuildConfig;

/**
 * Created by danhtran on 1/31/16.
 */
public class APIConfig {

    public static final String TERMS_URL = "https:///.../app_terms_and_conditions.pdf";
    public static final String POLICY_URL = "https://.../privacy_policy.pdf";

    public static final String DIRECTION_URL = "https://maps.googleapis.com/maps/api/directions/json";
    public static String ANONYMOUS_URL;

    static {
        switch (BuildConfig.FLAVOR) {
            case "develop":
                ANONYMOUS_URL = "";
                break;
            case "staging":
                ANONYMOUS_URL = "";
                break;
            case "production":
                ANONYMOUS_URL = "";
                break;
        }
    }

}
