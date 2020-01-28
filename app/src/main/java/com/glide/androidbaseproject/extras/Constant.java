package com.glide.androidbaseproject.extras;


import com.glide.androidbaseproject.MyApplication;

/**
 * Created by DanhTran on 6/2/2019.
 */
public class Constant {
    public static final String DATABASE_NAME = MyApplication.instance().getPackageName() + "_database";
    public static final String TERM = "https://mypsychicapp.com/#/tos";
    public static final String PRIVACY = "https://mypsychicapp.com/#/tos";

    public static final int REQUEST_CODE_RESULT_LOCATION_PERMISSION = 1000;
    public static final int REQUEST_CODE_RESULT_LOCATION_ON_OFF = 1001;
}
