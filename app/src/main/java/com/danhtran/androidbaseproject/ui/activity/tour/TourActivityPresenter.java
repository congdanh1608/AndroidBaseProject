package com.danhtran.androidbaseproject.ui.activity.tour;

import android.view.View;

import com.danhtran.androidbaseproject.MyApplication;
import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.database.share_preferences.SharedPrefsHelper;
import com.danhtran.androidbaseproject.extras.enums.SharePrefs;
import com.danhtran.androidbaseproject.ui.activity.BaseActivityPresenter;

import javax.inject.Inject;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class TourActivityPresenter extends BaseActivityPresenter implements TourActivityContract.Presenter {
    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    private TourActivityContract.View view;

    public TourActivityPresenter(TourActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void initInject() {
        MyApplication.instance().getAppComponent().inject(this);
    }

    @Override
    public void saveFlag() {
        //save into share preference
        sharedPrefsHelper.writeBoolean(SharePrefs.IS_NOT_FIRST_VIEW.getValue(), true);
    }

    public View.OnClickListener onClickListener() {
        return v -> {
            switch (v.getId()) {
                case R.id.btnDone:
                    view.moveNextTour();
                    break;
                case R.id.btnSkip:
                    view.launchHomeScreen();
                    break;
            }
        };
    }
}
