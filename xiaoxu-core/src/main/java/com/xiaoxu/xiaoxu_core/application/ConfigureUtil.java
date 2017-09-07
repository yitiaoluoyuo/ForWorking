package com.xiaoxu.xiaoxu_core.application;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by xiaoxu on 2017/8/24.
 *
 */

public final class ConfigureUtil {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    private static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getXiaoXuConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static String getImageHost(){
        return (String) getConfigurations().get(ConfigKeys.IMAGE_HOST);
    }

    public static Handler getHandler(){
        return (Handler) getConfigurations().get(ConfigKeys.HANDLER);
    }
}
