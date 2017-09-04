package com.xiaoxu.xiaoxu_core.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by xiaoxu on 2017/9/4.
 */

public class WebChromeClientImpl extends WebChromeClient {

    //拦截alert，做自己的弹窗处理
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

}
