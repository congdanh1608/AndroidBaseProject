package com.danhtran.androidbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.danhtran.androidbaseproject.ui.activity.BaseAppCompatActivity;

/**
 * Created by danhtran on 04/06/2017.
 */

public class UIUtils {
    /**
     * Show Dialog fragment
     *
     * @param dialogFragment dialog fragment
     * @param parent         parent fragment
     * @param tag            tag name of dialog fragment
     * @param requestCode    request code for result returned
     */
    public static void showDialogFragment(DialogFragment dialogFragment, Fragment parent, String tag, int requestCode) {
        FragmentManager fragmentManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            fragmentManager = parent.getFragmentManager();
        else fragmentManager = parent.getActivity().getSupportFragmentManager();
        dialogFragment.setTargetFragment(parent, requestCode);
        dialogFragment.show(fragmentManager, tag);
    }

    public static void showDialogFragment(DialogFragment dialogFragment, AppCompatActivity activity, String tag) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        Fragment prev = activity.getSupportFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, tag);
    }

    /**
     * Hide soft keyboard by context and view
     *
     * @param context context
     * @param view    view
     */
    public static void hideSoftKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hide soft keyboard by activity
     *
     * @param activity activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }


    /**
     * Show soft keyboard
     *
     * @param context context
     */
    public static void showSoftKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * Show soft keyboad with edit text
     *
     * @param editText edit text
     * @param context  context
     */
    public static void showSoftKeyboard(EditText editText, Context context) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void clearDialogFocus(Activity activity, View dialogLayout) {
        dialogLayout.setFocusable(true);
        dialogLayout.setFocusableInTouchMode(true);
        dialogLayout.requestFocus();
        hideSoftKeyboard(activity, dialogLayout);
    }

    /**
     * Clear focus for edit text
     *
     * @param editText edit text
     * @param activity AppCompatActivity
     */
    public static void clearFocus(EditText editText, BaseAppCompatActivity activity) {
        editText.clearFocus();
        clearFocus(activity, activity.getBinding().getRoot());
    }

    public static void clearFocus(Activity activity, View layout) {
        clearFocus(activity, layout, true);
    }

    public static void clearFocus(Activity activity, View layout, boolean hideKeyboard) {
        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.requestFocus();
        if (hideKeyboard) {
            hideSoftKeyboard(activity, layout);
        }
    }

    /**
     * Add keyboard event to hide soft keyboard
     *
     * @param activity   activity
     * @param rootLayout root layout
     * @param view       view
     */
    public static void addKeyboardEvents(final Activity activity, final View rootLayout, View view) {

        if (view instanceof EditText) {
            final EditText editText = (EditText) view;
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus && !isAnyOtherEditTextHasFocus(rootLayout, editText)) {
                        // If the edit text is lost focus and there is not any other edit text has focus,
                        // We hide the keyboard
                        hideSoftKeyboard(activity, v);
                    }
                }
            });
        }

        if (view instanceof ViewGroup) {
            //If a layout container, iterate over children
            ViewGroup viewGroup = (ViewGroup) view;
            for (int idx = 0; idx < viewGroup.getChildCount(); idx++) {
                View child = viewGroup.getChildAt(idx);
                addKeyboardEvents(activity, rootLayout, child);
            }
        }
    }

    //check is any other edit text has focus?
    private static boolean isAnyOtherEditTextHasFocus(View view, EditText editText) {

        boolean result = false;
        if (view != editText && view instanceof EditText && view.hasFocus()) {

            result = true;
        } else if (view instanceof ViewGroup) {

            //If a layout container, iterate over children
            ViewGroup viewGroup = (ViewGroup) view;
            for (int idx = 0; idx < viewGroup.getChildCount() && !result; idx++) {
                View child = viewGroup.getChildAt(idx);
                result = isAnyOtherEditTextHasFocus(child, editText);
            }
        }

        return result;
    }

    /**
     * Remove keyboard events to hide keyboard after click outside of edit text
     *
     * @param view view
     */
    public static void removeKeyboardEvents(View view) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.setOnFocusChangeListener(null);
        }

        if (view instanceof ViewGroup) {
            //If a layout container, iterate over children
            ViewGroup viewGroup = (ViewGroup) view;
            for (int idx = 0; idx < viewGroup.getChildCount(); idx++) {
                View child = viewGroup.getChildAt(idx);
                removeKeyboardEvents(child);
            }
        }
    }
}
