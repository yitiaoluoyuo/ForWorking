package com.xiaoxu.forworking.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.interceptors.DebugInterceptor;
import com.xiaoxu.xiaoxu_core.util.callback.CallbackManager;
import com.xiaoxu.xiaoxu_core.util.callback.CallbackType;
import com.xiaoxu.xiaoxu_core.util.callback.IGlobalCallback;
import com.xiaoxu.xiaoxu_ec.database.DatabaseManager;
import com.xiaoxu.xiaoxu_ec.icon.FontXiaoXuModule;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        ConfigureUtil.init(this)
                .withApiHost("http://happymmall.com/")
                .withLoaderDelayed(100)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontXiaoXuModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.testjson))
                .configure();

        DatabaseManager.getInstance().init(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(ConfigureUtil.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(ConfigureUtil.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(ConfigureUtil.getApplicationContext())) {
                            JPushInterface.stopPush(ConfigureUtil.getApplicationContext());
                        }
                    }
                });



    }
}
