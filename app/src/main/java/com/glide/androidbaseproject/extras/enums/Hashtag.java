package com.glide.androidbaseproject.extras.enums;


import com.glide.androidbaseproject.utils.EnumUtils;

/**
 * Created by danhtran on 09/04/2017.
 */

public class Hashtag {
    //    soibat://search?key=danh

    public enum Host {
        SEARCH("search"),
        DISCOVER("discover");

        private final String value;

        Host(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static Host fromValue(String value) {
            return EnumUtils.valueOf(Host.class, value);
        }
    }


    public enum Scheme {
        SOIBAT("soibat");

        private final String value;

        Scheme(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static Scheme fromValue(String value) {
            return EnumUtils.valueOf(Scheme.class, value);
        }
    }

    public enum Keyword {
        KEY("key"),
        GENRES("genres");

        private final String value;

        Keyword(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public static Keyword fromValue(String value) {
            return EnumUtils.valueOf(Keyword.class, value);
        }
    }
}
