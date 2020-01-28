
package com.glide.androidbaseproject.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glide.androidbaseproject.ui.customview.DividerItemDecoration;
import com.glide.androidbaseproject.ui.customview.SpacesItemDecoration;
import com.glide.customglide.GlideHelper;


/**
 * Created by danhtran on 2/20/16.
 */
public class BindingUtils {
    //size in dp
    @BindingAdapter({"absParams", "absWidth", "absHeight"})
    public static void absParams(ImageView imageView, boolean absParams, int width, int height) {
        width = SizeUtils.dpToPx(width);
        height = SizeUtils.dpToPx(height);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //size in dp
    @BindingAdapter({"linearParams", "linearWidth", "linearHeight"})
    public static void linearParams(ImageView imageView, boolean linearParams, int width, int height) {
        width = SizeUtils.dpToPx(width);
        height = SizeUtils.dpToPx(height);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //size in dp
    @BindingAdapter({"relativeParams", "relativeWidth", "relativeHeight"})
    public static void relativeParams(ImageView imageView, boolean relativeParams, int width, int height) {
        width = SizeUtils.dpToPx(width);
        height = SizeUtils.dpToPx(height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        imageView.setLayoutParams(layoutParams);
    }

    //no size
    @BindingAdapter({"setImage"})
    public static void setImage(ImageView imageView, String url) {
        if (url != null) {
            GlideHelper.getInstance().displayImage(url, imageView);
        }
    }

    //size in dp
    @BindingAdapter({"setImage", "width", "height"})
    public static void setImage(ImageView imageView, String url, int width, int height) {
        //set default value
        width = SizeUtils.dpToPx(width);
        height = SizeUtils.dpToPx(height);
        if (url != null) {
            GlideHelper.getInstance().displayImage(url, imageView, width, height);
        }
    }

    //size in dp
    @BindingAdapter({"setImage", "width", "height", "isCircle"})
    public static void setImage(ImageView imageView, String url, int width, int height, boolean isCircle) {
        //set default value
        width = SizeUtils.dpToPx(width);
        height = SizeUtils.dpToPx(height);
        if (url != null) {
            if (isCircle)
                GlideHelper.getInstance().displayImageCircle(url, imageView, width, height);
            else GlideHelper.getInstance().displayImage(url, imageView, width, height);
        }
    }

    @BindingAdapter({"setImage", "radius"})
    public static void radiusImage(ImageView imageView, String url, int radius) {
        if (url != null) {
            GlideHelper.getInstance().displayImageRounder(url, imageView, radius);
        }
    }

    //height in DP
    @BindingAdapter("heightOfView")
    public static void heightOfView(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = SizeUtils.dpToPx(height);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("linearManager")
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

    @BindingAdapter({"gridManager", "rows"})
    public static void gridManager(RecyclerView recyclerView, int _int, int spanCount) {
        switch (_int) {
            case 0:     //0 - grid layout
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spanCount));
                break;
            default:
                break;
        }
    }

    @BindingAdapter({"dividerItemLinear", "sizeSpace"})
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

    @BindingAdapter({"dividerItemGrid", "sizeSpace"})
    public static void dividerItemDecorationGrid(RecyclerView recyclerView, int column, int size) {
        recyclerView.addItemDecoration(new SpacesItemDecoration(size, column));
    }

    @BindingAdapter("hideKeyboadLostFocus")
    public static void closeKeyboard(EditText editText, boolean id) {
        final Context context = editText.getContext();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    UIUtils.hideSoftKeyboard(context, v);
                }
            }
        });
    }
}
