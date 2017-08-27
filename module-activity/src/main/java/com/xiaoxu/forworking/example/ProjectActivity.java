package com.xiaoxu.forworking.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.activities.ProxyActivity;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_ec.launcher.ILauncherListener;
import com.xiaoxu.xiaoxu_ec.launcher.LauncherDelegate;
import com.xiaoxu.xiaoxu_ec.launcher.LauncherFinishedTag;
import com.xiaoxu.xiaoxu_ec.main_delegates.IndexBottomBarDelegate;
import com.xiaoxu.xiaoxu_ec.sign.ISignSuccessListener;
import com.xiaoxu.xiaoxu_ec.sign.SignInDelegate;

public class ProjectActivity extends ProxyActivity implements
        ISignSuccessListener,ILauncherListener {

    @Override
    public XiaoXuDelegate setRootDelegate() {
        return new LauncherDelegate();

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
        Toast.makeText(this,"sign in success callback finished ",Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new IndexBottomBarDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"sign up success you can sign in ",Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new SignInDelegate());
        //注册成功后的一些业务（发送请求统计信息，计时）
    }

    @Override
    public void onLauncherFinished(LauncherFinishedTag tag) {

        switch (tag){
            case SIGNED:
                //登录成功的处理
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new IndexBottomBarDelegate());
                break;
            case SIGNED_NON:
                //没有登录做的处理
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new IndexBottomBarDelegate());
                break;
            default:
                break;
        }

    }
}
