package com.danhtran.androidbaseproject.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.danhtran.androidbaseproject.utils.UIUtils;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by danhtran on 2/12/17.
 */
public abstract class BaseFragment extends Fragment {

    protected ViewDataBinding binding;

    /**
     * set layout for this activity
     *
     * @return init
     */
    public abstract int setLayout();

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

    /**
     * change local config
     */
    public abstract void onConfigurationChanged();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int xml = setLayout();
        if (xml != 0) {
            binding = DataBindingUtil.inflate(inflater, xml, container, false);

            initUI();

            //hide keyboard after click outside of edit text
            getRootView().setClickable(true);
            getRootView().setFocusableInTouchMode(true);
            UIUtils.addKeyboardEvents(getActivity(), binding.getRoot(), binding.getRoot());

            //enable options menu
            setHasOptionsMenu(true);

            if (getArguments() != null) {
                loadPassedParamsIfNeeded(getArguments());
            }
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        UIUtils.removeKeyboardEvents(getRootView());
        super.onDestroy();
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

    /**
     * load passed params
     */
    protected void loadPassedParamsIfNeeded(@NonNull Bundle extras) {

    }

    /**
     * Show progress layout
     */
    public void showProgress() {
        if (getBaseActivity() != null) {
            getBaseActivity().showProgress();
        }
    }

    /**
     * Hide progress layout
     */
    public void hideProgress() {
        if (getBaseActivity() != null) {
            getBaseActivity().hideProgress();
        }
    }

    /**
     * Back pressed with delay time
     *
     * @param delayTime delay time
     */
    public void onBackPressed(int delayTime) {
        if (delayTime < 0) delayTime = 0;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, delayTime);
    }

    /**
     * Back pressed
     */
    public void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    /**
     * Get root view
     *
     * @return root view
     */
    public View getRootView() {
        return binding.getRoot();
    }

    /**
     * Get base activity if it is exits.
     *
     * @return BaseAppCompatActivity
     */
    public BaseAppCompatActivity getBaseActivity() {
        if (getActivity() instanceof BaseAppCompatActivity)
            return (BaseAppCompatActivity) getActivity();
        return null;
    }

    public BaseFragment getBaseFragment() {
        return this;
    }

    /**
     * Set title for appbar
     *
     * @param title title
     */
    public void setTitle(int title) {
        getBaseActivity().setTitle(title);
    }
}