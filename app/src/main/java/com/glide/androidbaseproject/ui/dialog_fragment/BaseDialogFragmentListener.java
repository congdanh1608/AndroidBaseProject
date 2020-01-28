package com.glide.androidbaseproject.ui.dialog_fragment;

import com.glide.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.glide.androidbaseproject.ui.fragment.BaseFragment;

/**
 * Created by DanhTran on 8/13/2019.
 */
public interface BaseDialogFragmentListener {
    void showProgress();

    void hideProgress();

    BaseAppCompatActivity getBaseActivity();

    BaseFragment getBaseFragment();

    BaseDialogFragment getBaseDialogFragment();
}
