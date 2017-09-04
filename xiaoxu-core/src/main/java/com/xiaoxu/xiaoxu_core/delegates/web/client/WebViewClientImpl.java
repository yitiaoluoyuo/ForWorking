package com.xiaoxu.xiaoxu_core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.delegates.IPageLoadListener;
import com.xiaoxu.xiaoxu_core.delegates.web.WebDelegate;
import com.xiaoxu.xiaoxu_core.delegates.web.route.Router;
import com.xiaoxu.xiaoxu_core.ui.loader.XiaoXuLoader;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;

/**
 * Created by xiaoxu on 2017/9/4.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = ConfigureUtil.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        XiaoXuLogger.d("shouldOverrideUrlLoading", url);
        //有A标签或者***，强制拦截，在原生中跳转
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        XiaoXuLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                XiaoXuLoader.stopLoading();
            }
        }, 1000);
    }


}
