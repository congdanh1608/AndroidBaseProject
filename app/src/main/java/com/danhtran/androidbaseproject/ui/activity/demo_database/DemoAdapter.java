package com.danhtran.androidbaseproject.ui.activity.demo_database;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.ViewGroup;

import com.danhtran.androidbaseproject.R;
import com.danhtran.androidbaseproject.databinding.DemoItemBinding;
import com.danhtran.androidbaseproject.ui.activity.demo_database.items.DemoItemData;
import com.danhtran.androidbaseproject.ui.activity.demo_database.items.DemoItemVH;
import com.danhtran.androidbaseproject.ui.baserecyclerview.BaseRecyclerAdapter;
import com.danhtran.androidbaseproject.ui.baserecyclerview.BaseRecyclerListener;
import com.danhtran.androidbaseproject.ui.baserecyclerview.BindingViewHolder;

import java.util.List;


/**
 * Created by danhtran on 10/06/2017.
 */

public class DemoAdapter extends BaseRecyclerAdapter<DemoItemData> {
    private final int VIEW_ITEM = 1;

    public DemoAdapter(Context context, List<DemoItemData> items, BaseRecyclerListener listener) {
        super(context, listener);
        this.items = items;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PROG) {
            return new BindingViewHolder(
                    DataBindingUtil.inflate(mLayoutInflater, R.layout.item_process_bar, parent, false));
        }
        return new BindingViewHolder(
                DataBindingUtil.inflate(mLayoutInflater, R.layout.demo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (getItemViewType(position) != VIEW_PROG) {
            DemoItemBinding binding = (DemoItemBinding) holder.getBinding();
            DemoItemVH itemVM = new DemoItemVH(items.get(position));
            binding.setHandler(itemVM);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) != null) {
            return VIEW_ITEM;
        } else
            return VIEW_PROG;
    }

    public void add(DemoItemData viewModel) {
        items.add(viewModel);
        notifyDataSetChanged();
    }

    public void add(int position, DemoItemData viewModel) {
        items.add(position, viewModel);
        notifyDataSetChanged();
    }

    public void set(List<DemoItemData> viewModels) {
        items.clear();
        addAll(viewModels);
    }

    public void addAll(List<DemoItemData> viewModels) {
        items.addAll(viewModels);
        notifyDataSetChanged();
    }
}