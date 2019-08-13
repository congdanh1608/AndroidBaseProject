package com.danhtran.androidbaseproject.ui.activity.tour;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityTourBinding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.danhtran.androidbaseproject.ui.activity.main.MainActivity;

/**
 * Created by DanhTran on 8/13/2019.
 */
public class TourActivity extends BaseAppCompatActivity implements TourActivityContract.View {
    private ActivityTourBinding mBinding;
    private TourActivityPresenter presenter;

    private MyViewPagerAdapter myViewPagerAdapter;
    private int[] layouts;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, TourActivity.class);
        return intent;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_tour;
    }

    @Override
    public void initUI() {
        mBinding = (ActivityTourBinding) binding;

        // layouts of all welcome sliders
        layouts = new int[]{
                R.layout.item_tour_slider_1,
                R.layout.item_tour_slider_2,
                R.layout.item_tour_slider_3,
        };

        // adding bottom dots
        addBottomDots(0);
        myViewPagerAdapter = new MyViewPagerAdapter(this, layouts);
        mBinding.viewPager.setAdapter(myViewPagerAdapter);
        mBinding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    @Override
    public void initData() {
        presenter = new TourActivityPresenter(this);
        mBinding.setPresenter(presenter);
        mBinding.executePendingBindings();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void moveNextTour() {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            mBinding.viewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    @Override
    public void launchHomeScreen() {
        presenter.saveFlag();

        startActivity(MainActivity.class.getName(), null);
    }

    private void addBottomDots(int currentPage) {
        switch (currentPage) {
            case 0: //left
                mBinding.layoutSlide.setGravity(Gravity.START);
                break;
            case 1: //center
                mBinding.layoutSlide.setGravity(Gravity.CENTER);
                break;
            case 2: //right
                mBinding.layoutSlide.setGravity(Gravity.END);
                break;
        }
    }

    private int getItem(int i) {
        return mBinding.viewPager.getCurrentItem() + i;
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                mBinding.btnDone.setVisibility(View.VISIBLE);
                mBinding.btnSkip.setVisibility(View.GONE);

                //last page
                presenter.saveFlag();
            } else {
                // still pages are left
                mBinding.btnDone.setVisibility(View.INVISIBLE);
                mBinding.btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
}
