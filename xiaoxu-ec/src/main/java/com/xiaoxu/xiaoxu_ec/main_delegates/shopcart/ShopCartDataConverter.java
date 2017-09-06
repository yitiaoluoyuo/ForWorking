package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/31.
 */

public class ShopCartDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convertToEntityList() {
        ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        JSONObject jsonObject = JSON.parseObject(getJsonData()).getJSONObject("data");
        final double cartTotalPrice = jsonObject.getDouble("cartTotalPrice");

        JSONArray cartProductArray = jsonObject.getJSONArray("cartProductVoList");

        final int size = cartProductArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = cartProductArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final int userId = data.getInteger("userId");
            final int productId = data.getInteger("productId");
            final int quantity = data.getInteger("quantity");
            final String productName = data.getString("productName");
            final String productSubtitle = data.getString("productSubtitle");
            final String productMainImage = data.getString("productMainImage");
            final double productPrice = data.getDouble("productPrice");
            final int productStatus = data.getInteger("productStatus");
            final double productTotalPrice = data.getDouble("productTotalPrice");
            final int productStock = data.getInteger("productStock");
            final int productChecked = data.getInteger("productChecked");
            final String limitQuantity = data.getString("limitQuantity");//LIMIT_NUM_SUCCESS,LIMIT_NUM_FAIL

            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.MAIN_IMAGE, productMainImage)
                    .setField(MultipleFields.SUBTITLE, productSubtitle)
                    .setField(MultipleFields.NAME, productName)
                    .setField(MultipleFields.QUANTITY, quantity)
                    .setField(MultipleFields.PRICE, productPrice)
                    .setField(MultipleFields.PRODUCT_ID, productId)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.ITEM_SHOP_CART)
                    .setField(MultipleFields.PRODUCT_CHECKED,productChecked)
                    .setField(MultipleFields.PRODUCT_TOTAL_PRICE,productTotalPrice)
                    .setField(MultipleFields.PRODUCT_STOCK,productStock)
                    .setField(MultipleFields.POSITION,i)
                    .build();
            dataList.add(entity);


        }
        return dataList;
    }
}
