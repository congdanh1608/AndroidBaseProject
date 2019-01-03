package com.danhtran.androidbaseproject.enums;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public class Header {

    public enum HeaderValue {
        AUTHORIZATION("Authorization");
        private final String value;

        HeaderValue(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public enum TypeHeader {
        BEARER("Bearer ");

        private final String value;

        TypeHeader(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }
}
