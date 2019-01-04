package com.danhtran.androidbaseproject.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by danhtran on 29/07/16.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static int SIZE = 1;
    private int sizeSpace = 1;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation, int sizeSpace) {
        this.sizeSpace = sizeSpace;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

   /* @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + SIZE;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + SIZE;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }*/

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
        if (mOrientation == VERTICAL_LIST) {
            setVertical(outRect, parent, view);
//                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());

        } else {
            setHorizontal(outRect, parent, view);
//                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
//    }

    private void setVertical(Rect outRect, RecyclerView parent, View view) {
        int pos = parent.getChildLayoutPosition(view);
        if (pos == parent.getAdapter().getItemCount() - 1) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, 0, sizeSpace);
        }
    }

    private void setHorizontal(Rect outRect, RecyclerView parent, View view) {
        int pos = parent.getChildLayoutPosition(view);
        if (pos == parent.getAdapter().getItemCount() - 1) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, sizeSpace, 0);
        }
    }
}
