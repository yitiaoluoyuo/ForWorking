package com.xiaoxu.xiaoxu_core.delegates.web.route;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoxu.xiaoxu_core.delegates.IPageLoadListener;
import com.xiaoxu.xiaoxu_core.delegates.web.IWebViewInitializer;
import com.xiaoxu.xiaoxu_core.delegates.web.WebDelegate;
import com.xiaoxu.xiaoxu_core.delegates.web.WebViewInitializer;
import com.xiaoxu.xiaoxu_core.delegates.web.chromeclient.WebChromeClientImpl;
import com.xiaoxu.xiaoxu_core.delegates.web.client.WebViewClientImpl;

/**
 * Created by xiaoxu on 2017/9/4.
 *
 */

public class WebDelegateImpl extends WebDelegate {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

   public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }


    @Override
    public WebChromeClient initWebChromeClient() {
        //webView页面内的处理
        return new WebChromeClientImpl();
    }
}
