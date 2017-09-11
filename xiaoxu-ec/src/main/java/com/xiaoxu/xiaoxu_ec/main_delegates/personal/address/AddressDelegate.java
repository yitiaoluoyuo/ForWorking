package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AddressDelegate extends MainDelegate implements ISuccess,View.OnClickListener {

    private AddressAdapter mAdapter = null;

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.tv_address_no_item)
    AppCompatTextView mTvNoItem = null;

    @BindView(R2.id.icon_address_add)
    IconTextView  iconTextView = null;

    @OnClick(R2.id.toolbar_address)
    void addAddress(){
        AddAddressDelegate addAddressDelegate = new AddAddressDelegate();
        getSupportDelegate().start(addAddressDelegate, ISupportFragment.SINGLETASK);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void request() {
        RestClient.builder()
                .url("/shipping/list.do")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            request();
        }
    }


    @Override
    public void onSuccess(String response) {
        XiaoXuLogger.json("json",response);

        int status = JSON.parseObject(response).getInteger("status");
        if (status != 0) {
            Toast.makeText(getContext(), "需要登录", Toast.LENGTH_LONG).show();
            //getParentDelegate().getSupportDelegate().start(new SignInDelegate());
        }else {
            //初始化RecycleView
            final LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(manager);

            final List<MultipleItemEntity> data =
                    new AddressDataConverter().setJsonData(response).convertToEntityList();
            mAdapter = new AddressAdapter(data,new AddressDelegate());
            mRecyclerView.setAdapter(mAdapter);
            checkItemCount();
        }


    }

    @Override
    public void onClick(View v) {

    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();

        if (count == 0) {
           mTvNoItem.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTvNoItem.setVisibility(View.GONE);

        }


    }
}
