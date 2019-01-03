package com.danhtran.androidbaseproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.utils.Utils;
import com.danhtran.androidbaseproject.view.activity.main.MainActivity;
import com.danhtran.androidbaseproject.view.fragment.BaseFragment;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by SilverWolf on 2/26/2018.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private View progressLayout;
    protected ViewDataBinding binding;
    protected FragmentManager mFragmentManager;
    private int backButtonCount = 0;

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

    /**
     * get View Data Binding
     *
     * @return ViewDataBinding
     */
    public ViewDataBinding getBinding() {
        return binding;
    }

    /**
     * Get Fragment Manager
     *
     * @return FragmentManager
     */
    public FragmentManager getMyFragmentManager() {
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

        //check and load intent params
        if (getIntent() != null && getIntent().getExtras() != null) {
            loadPassedParamsIfNeeded(getIntent().getExtras());
        }

        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //attach base context for calligraphy font
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
    protected void onPause() {
        super.onPause();
        unRegisterReceiver();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransitionExit();
    }

    @Override
    public void onBackStackChanged() {

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

    //load passed params
    protected void loadPassedParamsIfNeeded(@NonNull Bundle extras) {

    }

    /**
     * Register receiver in here
     */
    private void registerReceiver() {

    }

    /**
     * Unregister receiver in here
     */
    private void unRegisterReceiver() {

    }

    //init fragment manager
    private void initFragmentManager() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);
    }

    //call on root activity
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

    /**
     * set fragment for current activity
     *
     * @param tag  tag name
     * @param data data
     */
    public void setFragment(String tag, Object data) {
        setFragment(tag, data, true);
    }

    /**
     * set fragment for current activity
     *
     * @param tag            tag name
     * @param data           data
     * @param isAddBackStack does add this fragment into backstack?
     */
    public void setFragment(String tag, Object data, boolean isAddBackStack) {
        Boolean fragmentPopped = mFragmentManager.popBackStackImmediate(tag, 0);
        if (!fragmentPopped) {
            BaseFragment fragment = getFragment(tag, data);
            if (fragment != null) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.replace(R.id.content_fragment, fragment, tag);
                if (isAddBackStack)
                    transaction.addToBackStack(tag);
                transaction.commit();
            } else Logger.e("Forgot add fragment into base activity!");
        }
    }

    //get fragment by tag and data
    private BaseFragment getFragment(String tag, Object data) {
        /*if (LocationsFragment.class.getName().equals(tag)) {
            return LocationsFragment.instance();
        }*/
        return null;
    }

    /**
     * start activity without finish previous activity
     *
     * @param tag    tag name
     * @param bundle bundle
     */
    public void startActivity(String tag, Bundle bundle) {
        startActivity(tag, bundle, false);
    }

    /**
     * start activity and clear all others
     *
     * @param tag    tag name
     * @param bundle bundle
     */
    public void startActivityAndClearTop(String tag, Bundle bundle) {
        Intent intent = getIntentActivity(tag);

        if (intent != null) {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Start activity
     *
     * @param tag      tag name of activity
     * @param bundle   bundle
     * @param isFinish is finish previous activity?
     */
    public void startActivity(String tag, Bundle bundle, boolean isFinish) {
        Intent intent = getIntentActivity(tag);

        if (intent != null) {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
            if (isFinish) {
                finish();
            }
        }
    }

    //get intent of activity by tag
    private Intent getIntentActivity(String tag) {
        Intent intent = null;
        if (!TextUtils.isEmpty(tag)) {
            if (tag.equals(MainActivity.class.getName())) {
                intent = MainActivity.instance(this);
            }
        }
        return intent;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransitionEnter();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * Show progress layout
     */
    public void showProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.VISIBLE);
    }

    /**
     * Hide progress layout
     */
    public void hideProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.GONE);
    }

    /**
     * Get name of previous fragment
     *
     * @return name
     */
    public String getPreviousFragmentName() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count > 1)
            return mFragmentManager.getBackStackEntryAt(count - 2).getName();
        return null;
    }

    /**
     * Get name of current fragment
     *
     * @return name
     */
    public String getCurrentFragmentName() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count > 0)
            return mFragmentManager.getBackStackEntryAt(count - 1).getName();
        return null;
    }

    /**
     * Get current fragment
     *
     * @return fragment
     */
    public Fragment getCurrentFragment() {
        if (mFragmentManager != null) {
            String fragmentName = getCurrentFragmentName();
            if (!TextUtils.isEmpty(fragmentName))
                return mFragmentManager.findFragmentByTag(fragmentName);
        }
        return null;
    }

    /**
     * Get children manager
     *
     * @return FragmentManager
     */
    public FragmentManager getChildManager() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment.getChildFragmentManager();
        }
        return null;
    }

    /**
     * Get name of current children fragment
     *
     * @return name
     */
    public String getCurrentChildFragmentName() {
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
