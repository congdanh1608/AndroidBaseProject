package com.congdanh.androidbaseproject.utils;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.congdanh.androidbaseproject.view.activity.BaseAppCompatActivity;

/**
 * Created by SilverWolf on 04/06/2017.
 */

public class ViewUtils {
    public static int REQUEST_CODE_DEFAULT = 0;

    public static void showDialogFragment(DialogFragment dialogFragment, Fragment parent, String tag, int requestCode) {
        FragmentManager fragmentManager = parent.getActivity().getSupportFragmentManager();
        dialogFragment.setTargetFragment(parent, requestCode);
        dialogFragment.show(fragmentManager, tag);
    }

    public static void showDialogFragment(DialogFragment dialogFragment, BaseAppCompatActivity activity, String tag) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        dialogFragment.show(fragmentManager, tag);
    }
}
