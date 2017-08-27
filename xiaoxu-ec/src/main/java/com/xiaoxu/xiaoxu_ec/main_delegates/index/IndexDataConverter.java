package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 *
 *json数据的转化，处理，存储
 */

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        //*******************************************拿到json数据**
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONArray("list");
        final int size = dataArray.size();
        /*
                单一格式的数据处理
         */
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final int categoryId = data.getInteger("categoryId");
            final String name = data.getString("name");
            final String subtitle = data.getString("subtitle");
            final String mainImage = data.getString("mainImage");
            final float price = data.getFloat("price");
            final int status = data.getInteger("status");
            final String imageHost = data.getString("imageHost");

            //int type = 3;
            /*if (imageHost == null && subtitle != null) {
                type = ItemType.TEXT;
            } else if (imageHost != null && subtitle == null) {
                type = ItemType.IMAGE;
            } else if (imageHost != null) {
                type = ItemType.TEXT_IMAGE;
            }*/


            final MultipleItemEntity entity = MultipleItemEntity
                    .builder()
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.CATEGORY_ID,categoryId)
                    .setField(MultipleFields.NAME,name)
                    .setField(MultipleFields.SUBTITLE,subtitle)
                    .setField(MultipleFields.MAIN_IMAGE,mainImage)
                    .setField(MultipleFields.PRICE,price)
                    .setField(MultipleFields.STATUS,status)
                    .setField(MultipleFields.IMAGE_HOST,imageHost)
                    .setField(MultipleFields.SPAN_SIZE,2)
                    .setField(MultipleFields.ITEM_TYPE,3)
                    .build();
            ENTITIES_LIST.add(entity);
        }



        /*
                多格式的数据处理
         */

       /* final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final int id = data.getInteger("id");
            final int categoryId = data.getInteger("categoryId");

            final String imageHost = data.getString("imageHost");
            final String mainImage = data.getString("mainImage");

            final String subtitle = data.getString("subtitle");

            final int spanSize = data.getInteger("spanSize");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageHost == null && subtitle != null) {
                type = ItemType.TEXT;
            } else if (imageHost != null && subtitle == null) {
                type = ItemType.IMAGE;
            } else if (imageHost != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            //设置entity的值（与数据库的field一一对应）
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,subtitle)
                    .setField(MultipleFields.IMAGE_URL,imageHost)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .build();




            ENTITIES_LIST.add(entity);
        }*/

        return ENTITIES_LIST;
    }
}

