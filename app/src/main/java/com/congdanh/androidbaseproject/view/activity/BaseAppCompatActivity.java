package com.congdanh.androidbaseproject.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.FragmentUtils;
import com.congdanh.androidbaseproject.MyApplication;
import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.di.component.HasActivitySubcomponentBuilders;
import com.congdanh.androidbaseproject.view.activity.main.MainActivity;


/**
 * Created by congdanh on 2/26/2018.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private View progressLayout;
    protected ViewDataBinding binding;
    protected FragmentManager mFragmentManager;
    private String curFragment;
    ;

    public abstract int setLayout();

    public abstract View setProgressLayout();

    //set handler + execute view binding
    public abstract void initUI();

    public abstract void initData();

    public abstract void initListener();

    public ViewDataBinding getBinding() {
        return binding;
    }

    public FragmentManager getmFragmentManager() {
        return mFragmentManager;
    }

    protected void setupActivityComponent() {
        injectMembers(MyApplication.get(this));
    }

    protected abstract void injectMembers(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        //binding layout
        int xml = setLayout();
        if (xml != 0 && binding == null) {
            binding = DataBindingUtil.setContentView(this, xml);
        }
        //init
        initFragmentManager();
        initUI();
        //set progress layout
        progressLayout = setProgressLayout();
        initListener();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    public void onBackStackChanged() {
        if (mFragmentManager.getBackStackEntryCount() >= 1) {
            curFragment = mFragmentManager.getBackStackEntryAt(
                    mFragmentManager.getBackStackEntryCount() - 1).getName();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try {
            super.onRestoreInstanceState(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    //register receiver in here
    private void registerReceiver() {

    }

    //show progress layout
    public void showProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.VISIBLE);
    }

    //hide progress layout
    public void hideProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.GONE);
    }


    public String getPreviousNameFragment() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count > 1)
            return mFragmentManager.getBackStackEntryAt(count - 2).getName();
        return null;
    }

    public String getCurrentNameFragment() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count > 0)
            return mFragmentManager.getBackStackEntryAt(count - 1).getName();
        return null;
    }

    public Fragment getCurrentFragment() {
        if (mFragmentManager != null) {
            String currentNameFragment = getCurrentNameFragment();
            if (!TextUtils.isEmpty(currentNameFragment))
                return mFragmentManager.findFragmentByTag(currentNameFragment);
        }
        return null;
    }

    public FragmentManager getChildManager() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.getChildFragmentManager();
        }
        return null;
    }

    public String getCurrentNameChildFragment() {
        FragmentManager childManager = getChildManager();
        if (childManager != null) {
            int count = childManager.getBackStackEntryCount();
            if (count > 0) {
                return childManager.getBackStackEntryAt(count - 1).getName();
            }
        }
        return null;
    }

    public void addMyFragment(String tag, Object data, String title) {
        Fragment fragMain = mFragmentManager.findFragmentByTag(tag);
        boolean isPass = !tag.equals(curFragment);
        //except fragment movie + tvshow -> b/c i can open that fragment in similar.
        if (curFragment != null) {
            fragMain = null;
            isPass = true;
        }
        if (isPass) {
            //check in backstack -> get fragment from backstack
            if (fragMain != null) {
                mFragmentManager.popBackStack(tag, 0);
                return;
            }
            //add new fragment
           /* if (tag.equals(MapFragment.class.getName())) {
                fragMain = MapFragment.instance();
            } else if (tag.equals(MapPickFragment.class.getName())) {
                fragMain = MapPickFragment.instance();
            }*/


            if (fragMain != null) {
                FragmentUtils.replaceFragment(mFragmentManager, fragMain, R.id.content_fragment, true);
            }
        }
    }
}
