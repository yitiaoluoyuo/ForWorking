package com.xiaoxu.xiaoxu_ec.pay.textpay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/9.
 */

public class WeChatPayDelegate extends MainDelegate {

   @BindView(R2.id.tv_pay_title)
    AppCompatTextView tvPayTitle = null;

    @BindView(R2.id.image_qr_code)
    AppCompatImageView imageQrCode = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_pay_common;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        tvPayTitle.setText("微信支付");
        imageQrCode.setImageResource(R.mipmap.we_qr_code);

    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
