package com.glide.androidbaseproject.ui.base_recyclerview;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by danhtran on 10/06/2017.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected final T mBinding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public T getBinding() {
        return mBinding;
    }
}
