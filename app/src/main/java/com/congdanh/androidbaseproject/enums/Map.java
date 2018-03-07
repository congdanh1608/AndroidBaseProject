package com.congdanh.androidbaseproject.enums;

/**
 * Created by congdanh on 3/6/2018.
 */

public class Map {
        public enum Mode{
                DEFAULT(""),

                DRIVING("DRIVING"),
                DESTINATION("destination"),
                MODE("mode"),
                KEY("key");

                private final String value;

                private Mode(String value) {
                        this.value = value;
                }

                public String toString() {
                        return this.value;
                }
        }
}
