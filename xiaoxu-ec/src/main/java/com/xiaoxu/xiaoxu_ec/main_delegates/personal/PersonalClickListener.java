package com.xiaoxu.xiaoxu_ec.main_delegates.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.List.ListBean;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class PersonalClickListener extends SimpleClickListener {

    private final MainDelegate DELEGATE;

    public PersonalClickListener(MainDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        int id = bean.getId();
        switch (id) {
            case 1:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            case 2:
                DELEGATE.getParentDelegate().getSupportDelegate().start(bean.getDelegate());
                break;
            default:
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
