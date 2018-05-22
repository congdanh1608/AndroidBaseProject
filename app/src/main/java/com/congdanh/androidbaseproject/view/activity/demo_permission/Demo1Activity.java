package com.congdanh.androidbaseproject.view.activity.demo_permission;

import android.view.View;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.databinding.Demo1ActivityBinding;
import com.congdanh.androidbaseproject.view.activity.BaseAppCompatActivity;

/**
 * Created by congdanh on 2/28/2018.
 */

public class Demo1Activity extends BaseAppCompatActivity {
    Demo1ActivityBinding demo1ActivityBinding;
    Demo1Presenter demo1Presenter;

    @Override
    public int setLayout() {
        return R.layout.demo_1_activity;
    }

    @Override
    public View setProgressLayout() {
        return null;
    }

    @Override
    public void initUI() {
        demo1ActivityBinding = (Demo1ActivityBinding) binding;

        demo1Presenter = new Demo1Presenter(this);
        demo1ActivityBinding.setPresenter(demo1Presenter);
        demo1ActivityBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
