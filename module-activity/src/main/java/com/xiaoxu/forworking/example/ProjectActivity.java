package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.sign.ISignSuccessListener;
import com.xiaoxu.xiaoxu_ec.sign.SignUpDelegate;

public class ProjectActivity extends ProxyActivity implements ISignSuccessListener {

    @Override
    public XiaoXuDelegate setRootDelegate() {
        return new SignUpDelegate();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        //注册成功后的一些业务（发送请求统计信息，计时）
    }
}
