package com.congdanh.androidbaseproject.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.congdanh.androidbaseproject.R;
import com.congdanh.androidbaseproject.utils.Utils;
import com.congdanh.androidbaseproject.view.activity.main.MainActivity;
import com.congdanh.androidbaseproject.view.fragment.BaseFragment;
import com.orhanobut.logger.Logger;

/**
 * Created by congdanh on 2/26/2018.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private View progressLayout;
    protected ViewDataBinding binding;
    protected FragmentManager mFragmentManager;
    private int backButtonCount = 0;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        initData();
        initListener();
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

    //register receiver in here
    private void registerReceiver() {

    }

    private void initFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    private void exitApp() {
        //check exit app
        if (backButtonCount >= 1) {
            Utils.exitApp(this);
        } else {
            Toast.makeText(this, R.string.message_close_app, Toast.LENGTH_SHORT).show();
            backButtonCount++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backButtonCount = 0;
                }
            }, 3 * 1000);
        }
    }

    public void setFragment(String tag, Object data) {
        setFragment(tag, data, true);
    }

    public void setFragment(String tag, Object data, boolean isAddBackstack) {
        Boolean fragmentPopped = mFragmentManager.popBackStackImmediate(tag, 0);
        if (!fragmentPopped) {
            BaseFragment fragment = getFragment(tag, data);
            if (fragment != null) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.content_fragment, fragment, tag);
                if (isAddBackstack)
                    transaction.addToBackStack(tag);
                transaction.commit();
            } else Logger.e("Forgot add fragment into base activity!");
        }
    }

    private BaseFragment getFragment(String tag, Object data) {
        /*if (LocationsFragment.class.getName().equals(tag)) {
            return LocationsFragment.instance();
        }*/
        return null;
    }

    public void startActivity(String tag, Bundle bundle) {
        Intent intent = null;
        if (!TextUtils.isEmpty(tag)) {
            if (tag.equals(MainActivity.class.getName())) {
                intent = MainActivity.instance(this);
            }
        }

        if (intent != null) {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
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
}
