package com.xiaoxu.forworking.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xiaoxu.xiaoxu_core.app.XiaoXu;
import com.xiaoxu.xiaoxu_ec.icon.FontXiaoXuModule;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class Example extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XiaoXu.init(this)
                .withApiHost("")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontXiaoXuModule())
                .configure();

    }
}
