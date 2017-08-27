package com.xiaoxu.xiaoxu_core.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 * 简单工厂模式处理
 */

public class MultipleViewHolder extends BaseViewHolder {

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder crate(View view){
        return new MultipleViewHolder(view);
    }
}
