package com.danhtran.androidbaseproject.ui.activity.main;

import android.content.Intent;
import android.view.View;

import com.danhtran.androidbaseproject.ui.activity.demo_database.DemoActivity;

/**
 * Created by danhtran on 2/25/2018.
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
                }
                Intent intent = new Intent(mainActivity, activity);
                mainActivity.startActivity(intent);
            }
        };
    }

    public String getImage() {
        return "https://i.amz.mshcdn.com/FZXQWbpZ9hwycnowQwjI7zjejxk=/950x534/filters:quality(90)/https%3A%2F%2Fblueprint-api-production.s3.amazonaws.com%2Fuploads%2Fcard%2Fimage%2F528403%2F153ae5f3-17fa-4620-b8b1-33cd0d354385.jpg";
    }
}
