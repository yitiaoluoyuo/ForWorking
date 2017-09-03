package com.xiaoxu.xiaoxu_ec.main_delegates.personal.List.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/9/1.
 */

public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convertToEntityList() {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final String time = data.getString("time");


            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.PRICE, price)
                    .setField(MultipleFields.TIME,time)
                    .build();

            ENTITIES_LIST.add(entity);
        }

        return ENTITIES_LIST;
    }
}
