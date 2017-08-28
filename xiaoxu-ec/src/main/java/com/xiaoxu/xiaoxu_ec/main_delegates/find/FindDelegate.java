package com.xiaoxu.xiaoxu_ec.main_delegates.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemDelegate;
import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class FindDelegate extends ItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_observe;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
