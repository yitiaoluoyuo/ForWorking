package com.xiaoxu.xiaoxu_ec.main_delegates.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemDelegate;
import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class MineDelegate extends ItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
