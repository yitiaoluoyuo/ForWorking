package com.xiaoxu.forworking.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xiaoxu.xiaoxu_core.application.XiaoXu;
import com.xiaoxu.xiaoxu_ec.icon.FontXiaoXuModule;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XiaoXu.init(this)
                .withApiHost("http://news.baidu.com")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontXiaoXuModule())
                .configure();

    }
}
