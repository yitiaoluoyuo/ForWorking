package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_ec.launcher.ILauncherListener;
import com.xiaoxu.xiaoxu_ec.launcher.LauncherDelegate;
import com.xiaoxu.xiaoxu_ec.launcher.LauncherFinishedTag;
import com.xiaoxu.xiaoxu_ec.main_delegates.BottomBarDelegate;
import com.xiaoxu.xiaoxu_ec.sign.ISignSuccessListener;
import com.xiaoxu.xiaoxu_ec.sign.SignInDelegate;

import cn.jpush.android.api.JPushInterface;
import qiu.niorgai.StatusBarCompat;

public class IndexActivity extends ProxyActivity implements
        ISignSuccessListener,ILauncherListener {

    @Override
    public MainDelegate setRootDelegate() {
        return new LauncherDelegate();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ConfigureUtil.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    public void onSignInSuccess() {
        //getFragmentManager().popBackStack();
        getSupportDelegate().start(new BottomBarDelegate());

    }

    @Override
    public void onSignUpSuccess() {
        //注册成功后的一些业务（发送请求统计信息，计时）
        getFragmentManager().popBackStack();
        getSupportDelegate().start(new SignInDelegate());
    }

    @Override
    public void onLauncherFinished(LauncherFinishedTag tag) {

        switch (tag){
            case SIGNED:
                //登录成功的处理
                //getFragmentManager().popBackStack();
                getSupportDelegate().start(new BottomBarDelegate());
                break;
            case SIGNED_NON:
                //没有登录做的处理
                getFragmentManager().popBackStack();
                getSupportDelegate().start(new SignInDelegate());
                break;
            default:
                break;
        }

    }
}
