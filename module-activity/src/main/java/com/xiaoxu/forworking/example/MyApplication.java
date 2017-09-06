package com.xiaoxu.forworking.example;

import android.app.Application;
import android.support.annotation.Nullable;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xiaoxu.forworking.event.TestEvent;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.net.interceptors.AddCookieInterceptor;
import com.xiaoxu.xiaoxu_core.net.interceptors.DebugInterceptor;
import com.xiaoxu.xiaoxu_core.net.interceptors.SaveCookiesInterceptor;
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
                .withImageHost("http://img.happymmall.com/")
                .withLoaderDelayed(100)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontXiaoXuModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.testjson))
                .withInterceptor(new SaveCookiesInterceptor())
                .withInterceptor(new AddCookieInterceptor())
                .withJavascriptInterface("XiaoXu")
                .withWebEvent("test", new TestEvent())
                .configure();

        DatabaseManager.getInstance().init(this);
        //db查看工具
        initStetho();

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

        private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
