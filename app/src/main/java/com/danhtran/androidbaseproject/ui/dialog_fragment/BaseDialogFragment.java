package com.danhtran.androidbaseproject.ui.dialog_fragment;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by danhtran on 5/29/15.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected View rootView;
    protected ViewDataBinding binding;
    private View progressLayout;

    public abstract int setLayout();

    public abstract View setProgressLayout();

    public abstract TYPE setTypeScreen();

    public abstract void initUI();

    public abstract void initData();

    public abstract void initListener();

    public abstract void onConfigurationChanged();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);

        switch (setTypeScreen()) {
            case MATCH_PARENT:
                final RelativeLayout root = new RelativeLayout(getActivity());
                root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                dialog.getWindow().setContentView(root);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                break;
            case WRAP_CONTENT:
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                break;
        }
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int xml = setLayout();
        if (xml != 0 && binding == null) {
            binding = DataBindingUtil.inflate(inflater, xml, container, false);
            initUI();
            rootView = binding.getRoot();
            progressLayout = setProgressLayout();

            if (getArguments() != null) {
                loadPassedParamsIfNeeded(getArguments());
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onConfigurationChanged();
    }

    /**
     * load passed params
     */
    protected void loadPassedParamsIfNeeded(@NonNull Bundle extras) {

    }

    public void showProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.GONE);
    }

    public BaseAppCompatActivity getBaseActivity() {
        if (getActivity() instanceof BaseAppCompatActivity) {
            return (BaseAppCompatActivity) getActivity();
        }
        return null;
    }

    public enum TYPE {
        MATCH_PARENT(0),
        WRAP_CONTENT(1);

        private final int value;

        TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}
