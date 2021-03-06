package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleRecyclerAdapter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleViewHolder;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AddressAdapter extends MultipleRecyclerAdapter {

    private AddressDelegate mDelegate = null;

    ResetAddressDialog resetAddressDialog = null;

    protected AddressAdapter(List<MultipleItemEntity> data, AddressDelegate delegate) {
        super(data);
        this.mDelegate = delegate;
        addItemType(ItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_ADDRESS:
                final String name = entity.getField(MultipleFields.NAME);
                final String province = entity.getField(MultipleFields.PROVINCE);
                final String city = entity.getField(MultipleFields.CITY);
                final String address = entity.getField(MultipleFields.ADDRESS);
                final String phone = entity.getField(MultipleFields.PHONE);
                final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                final AppCompatTextView provinceCityText = holder.getView(R.id.tv_address_province_city);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView resetTextView = holder.getView(R.id.tv_address_reset);


                //添加删除点击事件
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int id = entity.getField(MultipleFields.ID);
                        RestClient.builder()
                                .url("/shipping/del.do")
                                .params("shippingId", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int code = JSON.parseObject(response).getInteger("status");

                                        if (code == 0){
                                            remove(holder.getLayoutPosition());
                                            Toast.makeText(ConfigureUtil.getApplicationContext(),"删除地址成功",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(ConfigureUtil.getApplicationContext(),"删除地址失败",Toast.LENGTH_SHORT).show();
                                        }



                                    }
                                })
                                .build()
                                .post();
                    }
                });

                //添加修改地址点击事件
                /*resetTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddressResetDelegate addressResetDelegate = new AddressResetDelegate();
                        mDelegate.getParentDelegate().getSupportDelegate().start(addressResetDelegate);
                    }
                });*/

                nameText.setText(name);
                phoneText.setText("电话：" + phone);
                addressText.setText(address);
                provinceCityText.setText(city);
                break;
            default:
                break;
        }
    }
}
