package com.xiaoxu.xiaoxu_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.application.XiaoXu;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;

/**
 * Created by xiaoxu on 2017/8/27.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout refreshLayout) {
        this.REFRESH_LAYOUT = refreshLayout;
        refreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        // TODO: 2017/8/27 全局handler的调用
        XiaoXu.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求，在请求返回的回调里，把 refresh progress 关闭
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },5000);
    }

    public static void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(XiaoXu.getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(XiaoXu.getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(XiaoXu.getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }
}
