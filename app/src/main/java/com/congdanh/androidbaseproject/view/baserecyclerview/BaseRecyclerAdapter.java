package com.congdanh.androidbaseproject.view.baserecyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.congdanh.androidbaseproject.databinding.ItemProcessBarBinding;

import java.util.List;

/**
 * Created by congdanh on 10/06/2017.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BindingViewHolder> {
    protected final int VISIBLE_THRESHOLD = 1;
    protected final int VIEW_PROG = 0;
    protected boolean isMoreLoading = false;
    protected List<T> items;
    protected BaseRecyclerListener listener;
    protected final LayoutInflater mLayoutInflater;

    //if we use Application Context in getSystemService(), We will get error if we have autolink in textview.
    public BaseRecyclerAdapter(Context context, BaseRecyclerListener listener) {
        this.listener = listener;
        mLayoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        if (getItemViewType(position) != VIEW_PROG) {
            if (position == getItemCount() - VISIBLE_THRESHOLD) {
                if (listener != null && !isMoreLoading) {
                    listener.onLoadMore(position);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void remove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void setProgressMore(final boolean isProgress) {
        if (items.size() > 0) {
            if (isProgress) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        items.add(null);
                        notifyItemInserted(items.size() - 1);
                    }
                });
            } else {
                items.remove(items.size() - 1);
                notifyItemRemoved(items.size());
                notifyItemRangeChanged(items.size(), getItemCount());
            }
        }
    }

    public void setMoreLoading(boolean moreLoading) {
        isMoreLoading = moreLoading;
    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ItemProcessBarBinding itemProcessBarBinding;

        ProgressViewHolder(View v) {
            super(v);
            itemProcessBarBinding = DataBindingUtil.bind(v.getRootView());
        }
    }
}