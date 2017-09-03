package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import android.support.v7.widget.AppCompatTextView;

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


    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_ADDRESS:
               final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(MultipleFields.PHONE);
                final String address = entity.getField(MultipleFields.ADDRESS);
                //final boolean isDefault = entity.getField(MultipleFields.TAG);
                //final int id = entity.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
              /*  deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("address.php")
                                .params("id", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(holder.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });*/

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
