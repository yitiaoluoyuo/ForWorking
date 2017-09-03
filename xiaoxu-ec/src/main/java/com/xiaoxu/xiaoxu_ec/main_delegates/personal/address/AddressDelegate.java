package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AddressDelegate extends XiaoXuDelegate implements ISuccess {

    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        RestClient.builder()
                .url("/product/list.do?keyword&categoryId=100001&orderBy=price_desc")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        List<MultipleItemEntity> data = new ArrayList<>();
        for (int i = 0; i < 18; i++) {

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.NAME, "无名")
                    .setField(MultipleFields.ADDRESS, "银河系太阳系地球村")
                    .setField(MultipleFields.PHONE, "110120119")
                    .build();
            data.add(entity);
        }

      /*  final List<MultipleItemEntity> data =
                new AddressDataConverter().setJsonData(response).convertToEntityList();*/
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
