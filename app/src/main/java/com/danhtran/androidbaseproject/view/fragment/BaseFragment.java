package com.danhtran.androidbaseproject.view.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.danhtran.androidbaseproject.view.activity.BaseAppCompatActivity;

/**
 * Created by SilverWolf on 2/12/17.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;
    protected ViewDataBinding binding;

    private View progressLayout;

    /**
     * set layout for this activity
     *
     * @return init
     */
    public abstract int setLayout();

    /**
     * set progress layout for this activity
     *
     * @return view
     */
    public abstract View setProgressLayout();

    /**
     * Set handler + execute view binding
     */
    public abstract void initUI();

    /**
     * Binding and initialize data into layout
     */
    public abstract void initData();

    /**
     * initialize the listener event
     */
    public abstract void initListener();

    public abstract void onConfigurationChanged();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int xml = setLayout();
        if (xml != 0) {
            binding = DataBindingUtil.inflate(inflater, xml, container, false);
            rootView = binding.getRoot();
            initUI();
            progressLayout = setProgressLayout();
            setHasOptionsMenu(true);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initListener();
        initData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onConfigurationChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //hide all item menu
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void showProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.GONE);
    }

    public void onBackPressed(int timeDelay) {
        if (timeDelay < 0) timeDelay = 0;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, timeDelay);
    }

    public void onBackPressed() {
        getActivity().onBackPressed();
    }

    public View getRootView() {
        return rootView;
    }

    public BaseAppCompatActivity getBaseActivity() {
        if (getActivity() instanceof BaseAppCompatActivity)
            return (BaseAppCompatActivity) getActivity();
        return null;
    }
}