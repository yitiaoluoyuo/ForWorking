package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class ProjectDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //以后对每一个控件做的操作
    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
