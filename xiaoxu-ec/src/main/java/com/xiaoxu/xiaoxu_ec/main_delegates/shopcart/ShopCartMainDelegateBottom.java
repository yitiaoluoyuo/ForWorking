package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_ec.R;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class ShopCartMainDelegateBottom extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
