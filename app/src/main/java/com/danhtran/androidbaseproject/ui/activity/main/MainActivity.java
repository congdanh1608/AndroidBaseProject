package com.danhtran.androidbaseproject.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.ActivityMainBinding;
import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;
import com.danhtran.androidbaseproject.utils.SnackBarUtils;
import com.danhtran.androidbaseproject.utils.UIUtils;

public class MainActivity extends BaseAppCompatActivity implements MainActivityListener{
    ActivityMainBinding mBinding;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initUI() {
        mBinding = (ActivityMainBinding) binding;
        MainActivityPresenter mainActivityPresenter = new MainActivityPresenter(this);
        mBinding.setPresenter(mainActivityPresenter);
        mBinding.executePendingBindings();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBinding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackBarUtils.showGeneralError(getRootView(), "test");
            }
        });

        mBinding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackBarUtils.showGeneralNotify(getRootView(), "test");
            }
        });

        mBinding.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mBinding.textInputLayout.setHintAnimationEnabled(true);
                    mBinding.textInputLayout.setHintEnabled(true);
                } else {
                    mBinding.textInputLayout.setHintAnimationEnabled(false);
                    mBinding.textInputLayout.setHintEnabled(false);
                }
            }
        });

        mBinding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.rlParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                UIUtils.clearFocus(mBinding.editText, MainActivity.this);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count <= 1) {
            exitApp();
        } else {
            super.onBackPressed();
        }
    }
}
