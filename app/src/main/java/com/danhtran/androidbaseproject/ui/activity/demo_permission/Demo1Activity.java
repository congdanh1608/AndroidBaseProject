package com.danhtran.androidbaseproject.ui.activity.demo_permission;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityDemo1Binding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by danhtran on 2/28/2018.
 */

public class Demo1Activity extends BaseAppCompatActivity {
    ActivityDemo1Binding activityDemo1Binding;
    Demo1Presenter demo1Presenter;

    @Override
    public int setLayout() {
        return R.layout.activity_demo_1;
    }

    @Override
    public void initUI() {
        activityDemo1Binding = (ActivityDemo1Binding) binding;

        demo1Presenter = new Demo1Presenter(this);
        activityDemo1Binding.setPresenter(demo1Presenter);
        activityDemo1Binding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
