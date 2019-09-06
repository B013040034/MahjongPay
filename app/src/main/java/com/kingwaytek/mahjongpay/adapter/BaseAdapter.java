package com.kingwaytek.mahjongpay.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by AslanYan on 2018/3/2.
 */

abstract class BaseAdapter<T, R extends ViewDataBinding> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    List<T> mTList;
    Context context;
    RecyclerViewClickListener clickListener = null;

    public BaseAdapter(Context context, List<T> list) {
        mTList = list;
        this.context = context;
    }

    abstract int getLayout();
    abstract void bind(T item, R viewDataBinding);

    @Override
    public BaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        R itemBinding = DataBindingUtil.inflate(layoutInflater, getLayout(), parent, false);
        return new BaseAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        T item = mTList.get(position);
        bind(item, (R) holder.getBinding());
    }

    public void setClickListener(RecyclerViewClickListener listener) {
        this.clickListener = listener;
    }

    public interface RecyclerViewClickListener<T> {
        void onItemClick(T item);
    }

    @Override
    public int getItemCount() {
        return mTList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        R mBinding;
        public ViewHolder(R binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public R getBinding() {
            return mBinding;
        }
    }

}
