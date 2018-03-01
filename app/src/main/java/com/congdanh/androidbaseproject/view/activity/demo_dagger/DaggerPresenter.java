package com.congdanh.androidbaseproject.view.activity.demo_dagger;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by congdanh on 2/28/2018.
 */

public class DaggerPresenter {
    private DaggerActivity daggerActivity;
    private DaggerListener daggerListener;
    private Context context;
    private String demo;

    @Inject
    public DaggerPresenter(DaggerActivity daggerActivity, DaggerListener daggerListener) {
        this.daggerActivity = daggerActivity;
        this.daggerListener = daggerListener;

        this.context = daggerActivity.getBaseContext();
    }

    //demo get data from Bundle...
    public void initialize(String demo) {
        this.demo = demo;

        //do something else
        //for example: load api to get database and show into adapter.
    }

    public View.OnClickListener onClickButtonListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "On Click", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public String text() {
        return "Dagger Activity";
    }
}
