package com.danhtran.androidbaseproject.ui.activity.main;

import android.view.View;

/**
 * Created by danhtran on 2/25/2018.
 */

public class MainActivityPresenter {
    private MainActivityListener listener;

    public MainActivityPresenter(MainActivityListener listener) {
        this.listener = listener;
    }

    public View.OnClickListener onClickListener(final int index) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    public String getImage() {
        return "https://i.amz.mshcdn.com/FZXQWbpZ9hwycnowQwjI7zjejxk=/950x534/filters:quality(90)/https%3A%2F%2Fblueprint-api-production.s3.amazonaws.com%2Fuploads%2Fcard%2Fimage%2F528403%2F153ae5f3-17fa-4620-b8b1-33cd0d354385.jpg";
    }
}
