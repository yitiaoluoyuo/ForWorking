package com.xiaoxu.xiaoxu_ec.main_delegates.personal.address;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/9/3.
 */

public class AddressDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convertToEntityList() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {

            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, isDefault)
                    .setField(MultipleFields.ADDRESS, address)
                    .setField(MultipleFields.PHONE, phone)
                    .build();
            ENTITIES_LIST.add(entity);
        }

        return ENTITIES_LIST;
    }
}
