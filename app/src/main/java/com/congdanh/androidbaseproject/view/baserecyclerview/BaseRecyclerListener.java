package com.congdanh.androidbaseproject.view.baserecyclerview;

/**
 * Created by congdanh on 11/06/2017.
 */

public interface BaseRecyclerListener {
    void onLoadMore(int position);

    <T extends Object> void onClickItem(T item);
}
