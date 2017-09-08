package com.xiaoxu.xiaoxu_ec.main_delegates.index.inside;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/8.
 */

public class ScanDelegate extends LatteDelegate {
    @BindView(R2.id.tv_common)
    AppCompatTextView tvCommon=null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_common;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tvCommon.setText("把手机放在水盆里，才能使用此功能");

    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
