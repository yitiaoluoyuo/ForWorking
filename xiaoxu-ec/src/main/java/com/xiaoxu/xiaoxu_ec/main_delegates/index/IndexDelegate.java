package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.application.XiaoXu;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.ui.refresh.RefreshHandler;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class IndexDelegate extends ItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

   /* @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }*/


    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mRefreshLayout);

        RestClient.builder()
                .url("/product/list.do?keyword&categoryId=100001&orderBy=price_desc")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        final ArrayList<MultipleItemEntity> list = converter.convert();
                       final int id = list.get(0).getField(MultipleFields.ID);
                        final String imageHost = list.get(0).getField(MultipleFields.IMAGE_HOST);

                        Toast.makeText(XiaoXu.getApplicationContext(),id+"  ,  "+imageHost,Toast.LENGTH_LONG).show();
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

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        //RefreshHandler.firstPage("/product/list.do?keyword&categoryId=100001&orderBy=price_desc");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
