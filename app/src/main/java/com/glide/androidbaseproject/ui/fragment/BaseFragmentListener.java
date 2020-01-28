package com.glide.androidbaseproject.ui.fragment;

import com.glide.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by DanhTran on 8/13/2019.
 */
public interface BaseFragmentListener {
    void showProgress();

    void hideProgress();

    BaseAppCompatActivity getBaseActivity();

    BaseFragment getBaseFragment();
}
