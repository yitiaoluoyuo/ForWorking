package com.xiaoxu.xiaoxu_ec.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by xiaoxu on 2017/8/28.
 *
 */

public class GoodsDetailDelegate extends XiaoXuDelegate {

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //设置水平打开的动画
        return new DefaultHorizontalAnimator();
        //super.onCreateFragmentAnimator()
    }
}
