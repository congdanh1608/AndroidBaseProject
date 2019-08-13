package com.danhtran.androidbaseproject.ui.activity.tour;

/**
 * Created by DanhTran on 8/13/2019.
 */
public interface TourActivityContract {
    interface View {
        void moveNextTour();

        void launchHomeScreen();
    }

    interface Presenter {
        void saveFlag();
    }
}
