package com.glide.androidbaseproject.extras.enums;

/**
 * Created by danhtran on 09/04/2017.
 */

public class Header {

    public enum HeaderKey {
        AUTHORIZATION("Authorization"),
        ACCEPT("Accept"),
        CONTENT_TYPE("Content-Type"),
        ;
        private final String value;

        HeaderKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum HeaderValue {
        BEARER("Bearer "),
        APPLICATION_JSON("application/json"),
        ;

        private final String value;

        HeaderValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
