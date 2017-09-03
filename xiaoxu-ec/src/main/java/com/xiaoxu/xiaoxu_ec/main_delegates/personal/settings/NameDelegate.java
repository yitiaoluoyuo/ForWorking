package com.xiaoxu.xiaoxu_ec.main_delegates.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/9/2.
 */

public class NameDelegate extends XiaoXuDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
