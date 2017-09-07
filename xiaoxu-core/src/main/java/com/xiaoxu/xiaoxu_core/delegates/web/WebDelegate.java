package com.xiaoxu.xiaoxu_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.xiaoxu.xiaoxu_core.application.ConfigKeys;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;
import com.xiaoxu.xiaoxu_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by xiaoxu on 2017/9/4.
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private LatteDelegate mTopDelegate = null;

    public WebDelegate() {
    }

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {

                // TODO: 2017/9/4  webView写在xml文件中，容易造成内存泄露 ，在代码中new出来实例，能很大程度避免泄露
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                //**********************************针对浏览器行为的控制
                mWebView.setWebViewClient(initializer.initWebViewClient());
                //**********************************针对内部页面行为的控制
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = ConfigureUtil.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(XiaoXuWebInterface.create(this), name);
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView IS NULL!");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }


}
