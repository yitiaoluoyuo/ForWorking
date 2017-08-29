package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.detail.GoodsDetailDelegate;

/**
 * Created by xiaoxu on 2017/8/28.
 * 为首页添加点击事件
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final XiaoXuDelegate DELEGATE;


    private IndexItemClickListener(XiaoXuDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(XiaoXuDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            final GoodsDetailDelegate delegate = GoodsDetailDelegate.create();
            DELEGATE.start(delegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}