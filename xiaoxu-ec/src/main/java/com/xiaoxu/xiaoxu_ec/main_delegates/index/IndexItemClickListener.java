package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_ec.detail.GoodsDetailDelegate;

/**
 * Created by xiaoxu on 2017/8/28.
 * 为首页添加点击事件
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final MainDelegate DELEGATE;


    private IndexItemClickListener(MainDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(MainDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        int type = entity.getField(MultipleFields.ITEM_TYPE);

        switch (type){
            case ItemType.TEXT:
                break;
            case ItemType.TEXT_IMAGE:
                final int goodsId = entity.getField(MultipleFields.ID);
                final GoodsDetailDelegate delegate = GoodsDetailDelegate.create(goodsId);
                DELEGATE.getSupportDelegate().start(delegate);
                break;
            case ItemType.BANNER:
                break;
            case ItemType.SINGLE_BIG_IMAGE:
                break;

        }

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
