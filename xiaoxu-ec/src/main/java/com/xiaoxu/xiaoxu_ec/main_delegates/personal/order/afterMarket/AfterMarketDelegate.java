package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order.afterMarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class AfterMarketDelegate extends MainDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_after_market;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
