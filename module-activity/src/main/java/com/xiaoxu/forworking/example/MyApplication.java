package com.xiaoxu.forworking.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xiaoxu.xiaoxu_core.application.XiaoXu;
import com.xiaoxu.xiaoxu_core.interceptors.DebugInterceptor;
import com.xiaoxu.xiaoxu_ec.database.DatabaseManager;
import com.xiaoxu.xiaoxu_ec.icon.FontXiaoXuModule;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XiaoXu.init(this)
                .withApiHost("http://happymmall.com")
                .withLoaderDelayed(5000)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontXiaoXuModule())
                .withInterceptor(new DebugInterceptor("index",R.raw.testjson))
                .configure();

        DatabaseManager.getInstance().init(this);

    }
}
