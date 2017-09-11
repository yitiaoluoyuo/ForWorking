package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
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
    public ArrayList<MultipleItemEntity> convertToEntityList() {

        //手动加入banner资源
        final ArrayList<String> bannerImages = new ArrayList<>();
        bannerImages.add("https://i8.mifile.cn/v1/a1/251f0880-423e-fa2d-3c18-1d3ec23f9912.webp");
        bannerImages.add("https://i8.mifile.cn/v1/a1/49dfd019-9504-abb5-34bb-26f425b67e45.webp");
        bannerImages.add("https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/b9540da01aef9a00a5c640b1c98b955a.jpg");
        final MultipleItemEntity entityBanner = MultipleItemEntity
                .builder()
                .setField(MultipleFields. BANNERS,bannerImages)
                .setField(MultipleFields.SPAN_SIZE,4)
                .setField(MultipleFields.ITEM_TYPE, ItemType.BANNER)
                .build();
        ENTITIES_LIST.add(entityBanner);


        //*******************************************拿到json数据**
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONObject("data").getJSONArray("list");
        final int size = dataArray.size();
        /*
                单一格式的数据处理
         */
        for (int j = 0; j < 100; j++) {


            for (int i = 1; i < size-1; i++) {
                final JSONObject data = dataArray.getJSONObject(i);
                final int id = data.getInteger("id");
                final int categoryId = data.getInteger("categoryId");
                final String name = data.getString("name");
                final String subtitle = data.getString("subtitle");
                final String mainImage = data.getString("mainImage");
                final double price = data.getDouble("price");
                final int status = data.getInteger("status");
                final String imageHost = data.getString("imageHost");

                //int type = 0;
            /*if (imageHost == null && subtitle != null) {
                type = ItemType.TEXT;
            } else if (imageHost != null && subtitle == null) {
                type = ItemType.IMAGE;
            } else if (imageHost != null) {
                type = ItemType.TEXT_IMAGE;
            }*/


                MultipleItemEntity entity = MultipleItemEntity
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
                        .setField(MultipleFields.ITEM_TYPE, ItemType.TEXT_IMAGE)
                        .build();
                ENTITIES_LIST.add(entity);
            }


            final MultipleItemEntity entityText = MultipleItemEntity
                    .builder()
                    .setField(MultipleFields.TEXT,"我是野广告"+j)
                    .setField(MultipleFields.SPAN_SIZE,4)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.TEXT)
                    .build();
            ENTITIES_LIST.add(entityText);

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

