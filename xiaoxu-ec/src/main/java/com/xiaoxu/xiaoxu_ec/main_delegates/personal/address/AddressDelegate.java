package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AddressDelegate extends XiaoXuDelegate implements ISuccess,View.OnClickListener {

    private AddressAdapter addressAdapter = null;






    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("/shipping/list.do")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        //初始化RecycleView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

      final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convertToEntityList();
        addressAdapter = new AddressAdapter(data,new AddressDelegate());
        mRecyclerView.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
