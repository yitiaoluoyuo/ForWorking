package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;

/**
 * Created by xiaoxu on 2017/8/28.
 * 展示分类商品
 */

public class SortContentDelegate extends XiaoXuDelegate {

    private static final String ARG_CATEGORY_ID = "CATEGORY_ID";
    private int mCategoryId = -1;

    //实例化时就传入categoryId fragment之间传递值非常实用的方法
    public static final SortContentDelegate instance(int categoryId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        final SortContentDelegate delegate = new SortContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mCategoryId = args.getInt(ARG_CATEGORY_ID);
        }
    }

    @Override
    public Object setLayout() {
        return com.xiaoxu.xiaoxu_ec.R.layout.delegate_sort_list_content;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
