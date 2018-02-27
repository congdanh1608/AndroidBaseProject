package com.congdanh.androidbaseproject.enums;


import com.congdanh.androidbaseproject.utils.EnumUtils;

/**
 * Created by SilverWolf on 09/04/2017.
 */

public class Hashtag {
    //    soibat://search?key=danh

    public enum Host {
        DEFAULT(""),

        SEARCH("search"),
        DISCOVER("discover");

        private final String value;

        private Host(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }

        public static Host fromValue(String value) {
            return EnumUtils.valueOf(Host.class, value);
        }
    }


    public enum Scheme {
        DEFAULT(""),

        SOIBAT("soibat");

        private final String value;

        private Scheme(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }

        public static Scheme fromValue(String value) {
            return EnumUtils.valueOf(Scheme.class, value);
        }
    }

    public enum Keyword {
        DEFAULT(""),

        KEY("key"),
        GENRES("genres");

        private final String value;

        private Keyword(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }

        public static Keyword fromValue(String value) {
            return EnumUtils.valueOf(Keyword.class, value);
        }
    }
}
