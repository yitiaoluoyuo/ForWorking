package com.xiaoxu.xiaoxu_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxu.xiaoxu_core.application.XiaoXu;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by xiaoxu on 2017/8/27.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{

    //
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mRecycleViewAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(
            SwipeRefreshLayout swipeRefreshLayout,
            RecyclerView recyclerView,
            DataConverter converter,
            PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(
            SwipeRefreshLayout swipeRefreshLayout,
            RecyclerView recyclerView,
            DataConverter converter) {
        return new RefreshHandler(
                swipeRefreshLayout,
                recyclerView,
                converter,
                new PagingBean());
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

    public void firstPage(String url){
        BEAN.setDelayed(3000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                       /* BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));*/
                        //设置Adapter
                        mRecycleViewAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        //todo **********************************??????????????????*******
                        mRecycleViewAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mRecycleViewAdapter);
                        //加一页
                        BEAN.addIndex();
                        Toast.makeText(XiaoXu.getApplicationContext(),"欢迎  光临！",Toast.LENGTH_LONG).show();
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

    @Override
    public void onLoadMoreRequested() {

    }
}