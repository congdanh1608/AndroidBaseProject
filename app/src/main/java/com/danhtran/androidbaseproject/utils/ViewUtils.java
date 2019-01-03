package com.danhtran.androidbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by SilverWolf on 04/06/2017.
 */

public class ViewUtils {
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
