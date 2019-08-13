package com.danhtran.androidbaseproject.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.ui.activity.main.MainActivity;
import com.danhtran.androidbaseproject.ui.activity.tour.TourActivity;
import com.danhtran.androidbaseproject.ui.fragment.BaseFragment;
import com.danhtran.androidbaseproject.utils.UIUtils;
import com.orhanobut.logger.Logger;

import java.util.Collection;
import java.util.LinkedHashSet;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by danhtran on 2/26/2018.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    protected ViewDataBinding binding;
    protected FragmentManager mFragmentManager;
    private int backButtonCount = 0;

    private Dialog progressDialog;
    protected Collection<Dialog> setOfDialogs = new LinkedHashSet<>();

    public void addDialog(Dialog dialog) {
        setOfDialogs.add(dialog);
    }

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
            //hide keyboard
            UIUtils.addKeyboardEvents(this, binding.getRoot(), binding.getRoot());
        }

        //init progress dialog
        createProgressDialog();

        //init
        initFragmentManager();
        initUI();

        //check and load intent params
        if (getIntent() != null && getIntent().getExtras() != null) {
            loadPassedParamsIfNeeded(getIntent().getExtras());
        }

        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
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
    protected void onDestroy() {
        if (binding != null) {
            UIUtils.removeKeyboardEvents(binding.getRoot());
        }

        for (Dialog dialog : setOfDialogs) {
            dialog.dismiss();
        }

        super.onDestroy();
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

    /**
     * load passed params
     */
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
    public void exitApp() {
        //check exit app
        if (backButtonCount >= 1) {
            finish();
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
        boolean fragmentPopped = mFragmentManager.popBackStackImmediate(tag, 0);
        if (!fragmentPopped) {
            BaseFragment fragment = getFragment(tag, data);
            if (fragment != null) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right);
                transaction.replace(R.id.content_fragment, fragment, tag);
                if (isAddBackStack)
                    transaction.addToBackStack(tag);
                transaction.commit();
            } else Logger.e("Forgot add fragment into base activity!");
        }
    }

    //get fragment by tag and data
    private BaseFragment getFragment(String tag, Object data) {
        /*if (Fragment1.class.getName().equals(tag)) {
            return new Fragment1();
        } */
        return null;
    }

    /**
     * start activity and finish current activity
     *
     * @param tag    tag name
     * @param bundle bundle
     */
    public void startActivity(String tag, Bundle bundle) {
        startActivity(tag, bundle, true);
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
                super.finish();     //don't user animation on here. because startActivity had already
            }
        }
    }

    /**
     * start activity and clear all others
     *
     * @param tag    tag name
     * @param bundle bundle
     */
    public void startActivityAsRoot(String tag, Bundle bundle) {
        Intent intent = getIntentActivity(tag);

        if (intent != null) {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            super.finish();
        }
    }

    public void startActivityForResult(String tag, Bundle bundle, int requestCode) {
        startActivityForResult(tag, bundle, true, requestCode);
    }

    public void startActivityForResult(String tag, Bundle bundle, boolean isFinish, int requestCode) {
        Intent intent = getIntentActivity(tag);

        if (intent != null) {
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
            if (isFinish) {
                super.finish();
            }
        }
    }

    /**
     * get intent of activity by tag
     *
     * @param tag tag is tag of activity contain the intent
     * @return intent
     */
    private Intent getIntentActivity(String tag) {
        Intent intent = null;
        if (!TextUtils.isEmpty(tag)) {
            if (tag.equals(MainActivity.class.getName())) {
                intent = MainActivity.createIntent(this);
            } else if (tag.equals(TourActivity.class.getName())) {
                intent = TourActivity.createIntent(this);
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

    //region Show Background Progress
    private void createProgressDialog() {
        progressDialog = new Dialog(this, R.style.DialogFullScreen);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.process_dialog, null);
        //show view
        View view = dialogView.findViewById(R.id.progressBar);
        view.setVisibility(View.VISIBLE);

        progressDialog.setContentView(dialogView);
        progressDialog.setCancelable(false);

        Window dialogWindow = progressDialog.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        addDialog(progressDialog);
    }

    /**
     * Show progress layout
     */
    public void showProgress() {
        if (progressDialog != null)
            progressDialog.show();
    }

    /**
     * Hide progress layout
     */
    public void hideProgress() {
        if (progressDialog != null)
            progressDialog.hide();
    }

    /**
     * get root view of this activity
     *
     * @return view
     */
    public View getRootView() {
        if (binding != null) {
            return binding.getRoot();
        }
        return null;
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
