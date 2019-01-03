package com.danhtran.androidbaseproject.view.customview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SilverWolf on 17/06/2017.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int column;

    public SpacesItemDecoration(int space, int column) {
        this.space = space;
        this.column = column;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int pos = parent.getChildLayoutPosition(view);

        // add top margin
        if (pos < column) {
            outRect.top = 0;
        } else {
            outRect.top = space;
        }

        //bottom
        int rows = pos / column;
        int endRows = (pos - (rows * column) % column);
        if (endRows < column) {
            outRect.bottom = 0;
        } else {
            outRect.bottom = space;
        }

        //left
        if (pos % column == 0) {
            outRect.left = 0;
        } else {
            outRect.left = space;
        }

        //right
        if (pos % column == column - 1) {
            outRect.right = 0;
        } else {
            outRect.right = space;
        }
    }
}
