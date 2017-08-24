package com.xiaoxu.xiaoxu_core.application;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by xiaoxu on 2017/8/24.
 *
 */

public final class XiaoXu {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getXiaoXuConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
