package com.xiaoxu.xiaoxu_core.application;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xiaoxu.xiaoxu_core.delegates.web.event.Event;
import com.xiaoxu.xiaoxu_core.delegates.web.event.EventManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by xiaoxu on 2017/8/24.
 * 配置文件的存储和获取
 */

public class Configurator {

    //配置参数的容器
    private static final HashMap<Object, Object> XiaoXu_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    //字体图标容器
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器容器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        XiaoXu_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        XiaoXu_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    final HashMap<Object, Object> getXiaoXuConfigs() {
        return XiaoXu_CONFIGS;
    }

    //静态内部类的单例创建
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //配置完成时调用
    public final void configure() {

        //配置完成时，进行字体图标的初始化
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        XiaoXu_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        //必须在CONFIG_READY之后执行
        Utils.init(ConfigureUtil.getApplicationContext());
    }

    public final Configurator withApiHost(String host) {
        XiaoXu_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        XiaoXu_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    //初始化字体图标
    private void initIcons() {
        if (ICONS.size() > 0) {
            //初始化第一个图标（配合构造方法传入参数的限制）
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        //加入自己的图标
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        XiaoXu_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        XiaoXu_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /*public final Configurator withWeChatAppId(String appId) {
        XiaoXu_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }*/

    /*public final Configurator withWeChatAppSecret(String appSecret) {
        XiaoXu_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }*/

    public final Configurator withActivity(Activity activity) {
        XiaoXu_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    public final Configurator withCacheDir(File cacheDir) {
        XiaoXu_CONFIGS.put(ConfigKeys.CACHE_DIR, cacheDir);
        return this;
    }

   public Configurator withJavascriptInterface(@NonNull String name) {
        XiaoXu_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    //检查配置是否完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) XiaoXu_CONFIGS.get(ConfigKeys.CONFIG_READY);
        //TODO 设置一些自定义异常方便定位异常
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = XiaoXu_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) XiaoXu_CONFIGS.get(key);
    }
}
