package com.congdanh.androidbaseproject.view.activity.main;

import android.content.Intent;
import android.view.View;

import com.congdanh.androidbaseproject.view.activity.demo.DemoActivity;

/**
 * Created by congd on 2/25/2018.
 */

public class MainActivityPresenter {
    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, DemoActivity.class);
                mainActivity.startActivity(intent);
            }
        };
    }
}
