package com.danhtran.androidbaseproject.ui.baserecyclerview;

/**
 * Created by danhtran on 11/06/2017.
 */

public interface BaseRecyclerListener {
    void onLoadMore(int position);

    <T extends Object> void onClickItem(T item);
}
