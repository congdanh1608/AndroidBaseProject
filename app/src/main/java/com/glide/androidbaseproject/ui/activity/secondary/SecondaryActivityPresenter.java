package com.glide.androidbaseproject.ui.activity.secondary;

import com.glide.androidbaseproject.ui.activity.BaseActivityPresenter;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class SecondaryActivityPresenter extends BaseActivityPresenter {
    private SecondaryActivityListener listener;

    public SecondaryActivityPresenter(SecondaryActivityListener listener, Object bundle) {
        this.listener = listener;

        initFragment(bundle);
    }

    @Override
    public void initInject() {

    }

    private void initFragment(Object bundle) {
        String tagFragment = listener.getFragmentTag();
        if (tagFragment != null) {
            listener.getBaseActivity().setFragment(tagFragment, bundle);
        }
    }
}
