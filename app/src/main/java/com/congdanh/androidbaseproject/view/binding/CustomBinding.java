
package com.congdanh.androidbaseproject.view.binding;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.congdanh.androidbaseproject.utils.DimensionUtils;
import com.congdanh.androidbaseproject.view.customview.DividerItemDecoration;
import com.congdanh.androidbaseproject.view.customview.SpacesItemDecoration;

import vn.danhtran.customglide.GlideImageLoader;


/**
 * Created by congdanh on 2/20/16.
 */
public class CustomBinding {
    //size in dp
    @BindingAdapter({"bind:absParams", "bind:absWidth", "bind:absHeight"})
    public static void absParams(ImageView imageView, boolean absParams, int width, int height) {
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //size in dp
    @BindingAdapter({"bind:linearParams", "bind:linearWidth", "bind:linearHeight"})
    public static void linearParams(ImageView imageView, boolean linearParams, int width, int height) {
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //size in dp
    @BindingAdapter({"bind:relativeParams", "bind:relativeWidth", "bind:relativeHeight"})
    public static void relativeParams(ImageView imageView, boolean relativeParams, int width, int height) {
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //no size
    @BindingAdapter({"bind:setImage"})
    public static void setImage(ImageView imageView, String url) {
        if (url != null) {
            GlideImageLoader.getInstance().displayImage(url, imageView);
        }
    }

    //size in dp
    @BindingAdapter({"bind:setImage", "bind:width", "bind:height"})
    public static void setImage(ImageView imageView, String url, int width, int height) {
        //set default value
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        if (url != null) {
            GlideImageLoader.getInstance().displayImage(url, imageView, width, height);
        }
    }

    //size in dp
    @BindingAdapter({"bind:setImage", "bind:width", "bind:height", "bind:isCircle"})
    public static void setImage(ImageView imageView, String url, int width, int height, boolean isCircle) {
        //set default value
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        if (url != null) {
            if (isCircle)
                GlideImageLoader.getInstance().displayImageCircle(url, imageView, width, height);
            else GlideImageLoader.getInstance().displayImage(url, imageView, width, height);
        }
    }

    //size in dp
    @BindingAdapter({"bind:setImage", "bind:width", "bind:height", "bind:isCircle", "bind:borderSize"})
    public static void setImage(ImageView imageView, String url, int width, int height, boolean isCircle, int borderSize) {
        //set default value
        width = DimensionUtils.dpToPx(width);
        height = DimensionUtils.dpToPx(height);
        if (url != null) {
            if (isCircle)
                GlideImageLoader.getInstance().displayImageCircle(url, imageView, width, height, borderSize);
            else GlideImageLoader.getInstance().displayImage(url, imageView, width, height);
        }
    }

    @BindingAdapter({"bind:setImage", "bind:radius"})
    public static void radiusImage(ImageView imageView, String url, int raidus) {
        if (url != null) {
            GlideImageLoader.getInstance().displayImageRounder(url, imageView, raidus);
        }
    }

    //height in DP
    @BindingAdapter("bind:heightOfView")
    public static void heightOfView(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = DimensionUtils.dpToPx(height);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("bind:linearManager")
    public static void linearManager(RecyclerView recyclerView, int _int) {
        switch (_int) {
            case 0:     //0 - horizontal
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                        LinearLayoutManager.HORIZONTAL, false));
                break;
            case 1:     //1 - vertical
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                        LinearLayoutManager.VERTICAL, false));
                break;
            case 2:     //2 - no scroll vertical
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                break;
            case 3:     //3 - no scroll horizontal
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()) {
                    @Override
                    public boolean canScrollHorizontally() {
                        return false;
                    }
                });
                break;
            default:
                break;
        }
    }

    @BindingAdapter({"bind:gridManager", "bind:rows"})
    public static void gridManager(RecyclerView recyclerView, int _int, int spanCount) {
        switch (_int) {
            case 0:     //0 - grid layout
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spanCount));
                break;
            default:
                break;
        }
    }

    @BindingAdapter({"bind:dividerItemLinear", "bind:sizeSpace"})
    public static void dividerItemDecorationLinear(RecyclerView recyclerView, int type, int size) {
        Context context = recyclerView.getContext();
        switch (type) {
            case 0:
                recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL, size));
                break;
            case 1:
                recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, size));
                break;
            default:
                break;
        }
    }

    @BindingAdapter({"bind:dividerItemGrid", "bind:sizeSpace"})
    public static void dividerItemDecorationGrid(RecyclerView recyclerView, int column, int size) {
        recyclerView.addItemDecoration(new SpacesItemDecoration(size, column));
    }

    @BindingAdapter("bind:hideKeyboadLostFocus")
    public static void closeKeyboard(EditText editText, boolean id) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    static private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
