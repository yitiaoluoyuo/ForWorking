package com.xiaoxu.xiaoxu_ec.main_delegates.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AboutDelegate extends MainDelegate {

    @BindView(R2.id.tv_info)
    AppCompatTextView mTextView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_about;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

        mTextView.setText("这里做一些项目简介和个人简介");
    }
}
