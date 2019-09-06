package com.kingwaytek.mahjongpay.util;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by AslanYan on 2018/3/9.
 */

/**
 * 使用範例：
 * RecycleViewBuilder recycleViewBuilder = new RecycleViewBuilder(binding.recycleExample, myAdapter, 2);
 * recycleViewBuilder
 * .setScrollable(false)
 * .setHasDivider(false)
 * .build();
 */
public class RecycleViewBuilder {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private Context mContext;
    private boolean canScroll = true;
    private boolean hasDivider = true;
    private RecyclerView.ItemDecoration itemDecoration = null;
    private int orientation = LinearLayout.VERTICAL;
    private int spanCount = 0;

    /**
     * 呈現 listView 效果時使用
     * <p>
     * 初始化完成建置時請呼叫建置func {@link #build()}
     */
    public RecycleViewBuilder(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        mContext = recyclerView.getContext();
    }

    /**
     * 呈現 gridView 效果時使用
     *
     * @param spanCount 設定一列中有幾個 item
     *                  初始化完成建置時請呼叫建置func {@link #build()}
     */
    public RecycleViewBuilder(RecyclerView recyclerView, RecyclerView.Adapter adapter, int spanCount) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.spanCount = spanCount;
        mContext = recyclerView.getContext();
    }

    /**
     * 預設為可滑動
     */
    public RecycleViewBuilder setScrollable(boolean canScroll) {
        this.canScroll = canScroll;
        return this;
    }

    /**
     * 預設為有分隔線
     */
    public RecycleViewBuilder setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
        return this;
    }

    /**
     * 預設為垂直
     */
    public RecycleViewBuilder setOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    /**
     * 預設客製化分隔線
     */
    public RecycleViewBuilder setCustomItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        return this;
    }

    public void build() {
        LinearLayoutManager layoutManager;
        if (spanCount != 0) {
            layoutManager = new GridLayoutManager(mContext, spanCount, orientation, false);
        } else {
            layoutManager = getLinearLayoutManager();
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(canScroll);
        if (hasDivider) {
            if (itemDecoration == null) {
                recyclerView.addItemDecoration(getDividerItemDecoration(layoutManager));
            } else {
                recyclerView.addItemDecoration(itemDecoration);
            }
        }
        recyclerView.setAdapter(adapter);

    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(mContext, orientation, false);
    }

    private DividerItemDecoration getDividerItemDecoration(LinearLayoutManager layoutManager) {
        return new DividerItemDecoration(mContext, layoutManager.getOrientation());
    }
}
