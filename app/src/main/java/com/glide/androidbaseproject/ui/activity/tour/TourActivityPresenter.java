package com.glide.androidbaseproject.ui.activity.tour;

import android.view.View;

import com.glide.androidbaseproject.MyApplication;
import com.glide.androidbaseproject.R;
import com.glide.androidbaseproject.database.share_preferences.SharedPrefsHelper;
import com.glide.androidbaseproject.extras.enums.SharePrefs;
import com.glide.androidbaseproject.ui.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class TourActivityPresenter extends BaseActivityPresenter {
    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    private TourActivityListener listener;

    public TourActivityPresenter(TourActivityListener listener) {
        this.listener = listener;
    }

    @Override
    public void initInject() {
        MyApplication.instance().getAppComponent().inject(this);
    }

    void saveFlag() {
        //save into share preference
        sharedPrefsHelper.writeBoolean(SharePrefs.IS_NOT_FIRST_VIEW.getValue(), true);
    }

    public View.OnClickListener onClickListener() {
        return v -> {
            switch (v.getId()) {
                case R.id.btnDone:
                    listener.moveNextTour();
                    break;
                case R.id.btnSkip:
                    listener.launchHomeScreen();
                    break;
            }
        };
    }
}
