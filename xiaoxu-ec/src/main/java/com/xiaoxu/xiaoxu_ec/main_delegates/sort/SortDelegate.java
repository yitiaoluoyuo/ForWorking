package com.xiaoxu.xiaoxu_ec.main_delegates.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemMainDelegate;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.list.SortListDelegate;

/**
 * Created by xiaoxu on 2017/8/26.
 * sort page
 */

public class SortDelegate extends ItemMainDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final SortListDelegate listDelegate = new SortListDelegate();
        loadRootFragment(R.id.sort_vertical_list_container,listDelegate);
        //默认显示分类  100001
      /*getSupportDelegate()
              .loadRootFragment(R.id.sort_vertical_list_container, SortContentDelegate.instance(10001));*/

    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}