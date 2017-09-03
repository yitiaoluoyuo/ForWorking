package com.xiaoxu.xiaoxu_ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class GoodsInfoDelegate extends XiaoXuDelegate {

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mGoodsInfoTitle = null;
    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mGoodsInfoDesc = null;
    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mGoodsInfoPrice = null;


    private static final String ARG_GOODS_DATA = "ARG_GOODS_DATA";
    private JSONObject mData = null;


    public static GoodsInfoDelegate create(String goodsInfo){
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_DATA,goodsInfo);
        GoodsInfoDelegate delegate = new GoodsInfoDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        final String goodsData = args.getString(ARG_GOODS_DATA);
        mData = JSON.parseObject(goodsData);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final String name = mData.getString("name");
        final String subtitle = mData.getString("subtitle");
        final double price = mData.getDouble("price");
        mGoodsInfoTitle.setText(name);
        mGoodsInfoDesc.setText(subtitle);
        mGoodsInfoPrice.setText(String.valueOf(price));

    }
}
