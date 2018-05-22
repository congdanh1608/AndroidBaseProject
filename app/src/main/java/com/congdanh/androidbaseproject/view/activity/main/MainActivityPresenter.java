package com.congdanh.androidbaseproject.view.activity.main;

import android.content.Intent;
import android.view.View;

import com.congdanh.androidbaseproject.view.activity.demo_database.DemoActivity;
import com.congdanh.androidbaseproject.view.activity.demo_permission.Demo1Activity;

/**
 * Created by congdanh on 2/25/2018.
 */

public class MainActivityPresenter {
    private MainActivity mainActivity;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public View.OnClickListener onClickListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class activity = null;
                switch (index) {
                    case 0:
                    default:
                        activity = DemoActivity.class;
                        break;
                    case 1:
                        activity = Demo1Activity.class;
                        break;
                }
                Intent intent = new Intent(mainActivity, activity);
                mainActivity.startActivity(intent);
            }
        };
    }
}
