package com.xiaoxu.xiaoxu_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.xiaoxu.xiaoxu_core.delegates.web.event.Event;
import com.xiaoxu.xiaoxu_core.delegates.web.event.EventManager;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;

/**
 * Created by xiaoxu on 2017/9/4.
 * 和原生页面进行交互
 */

public class XiaoXuWebInterface {

    private final WebDelegate DELEGATE;

    private XiaoXuWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static XiaoXuWebInterface create(WebDelegate delegate) {
        return new XiaoXuWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
       final Event event = EventManager.getInstance().createEvent(action);
        XiaoXuLogger.d("WEB_EVENT",params);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
