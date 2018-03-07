package com.congdanh.androidbaseproject.view.dialogfragment;

import android.app.Dialog;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by congdanh on 5/29/15.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    protected View rootView;
    protected ViewDataBinding binding;
    private View progressLayout;
    protected Dialog dialog;

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
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final RelativeLayout root = new RelativeLayout(getActivity());
        Window window = dialog.getWindow();
        if (window != null) {
            window.setContentView(root);
            switch (setTypeScreen()) {
                case MATCH_PARENT:
                    root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    break;
                case WRAP_CONTENT:
                    root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    break;
            }
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int xml = setLayout();
        if (xml != 0 && binding == null) {
            binding = DataBindingUtil.inflate(inflater, xml, container, false);
            rootView = binding.getRoot();
            initUI();
            progressLayout = setProgressLayout();
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

    public void showProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressLayout != null)
            progressLayout.setVisibility(View.GONE);
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
