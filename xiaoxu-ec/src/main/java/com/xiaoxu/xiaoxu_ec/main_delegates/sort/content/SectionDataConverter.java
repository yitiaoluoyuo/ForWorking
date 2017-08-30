package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/30.
 */

public class SectionDataConverter {


    public ArrayList<SectionHeadEntity> convertToEntityList(String json) {

        final ArrayList<SectionHeadEntity> dataList = new ArrayList<>();


        //本地加入head数据
        String titleData = "标题";
        ArrayList<String> titleList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            titleData += i + 1;
            titleList.add(titleData);
            titleData = titleData.substring(0, 2);
        }


        final int size = titleList.size();
        for (int i = 0; i < size; i++) {
            //final JSONObject data = dataArray.getJSONObject(i);
            //final int id = data.getInteger("id");
            //final String title = data.getString("section");
            final String title = titleList.get(i);

            //添加title
            final SectionHeadEntity sectionHeadEntity = new SectionHeadEntity(true, title);
            sectionHeadEntity.setId(i);
            sectionHeadEntity.setIsMore(true);
            dataList.add(sectionHeadEntity);

            JSONArray goods = JSON.parseObject(json).getJSONObject("data").getJSONArray("list");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int id = contentItem.getInteger("id");
                final String subtitle = contentItem.getString("subtitle");
                final String imageHost = contentItem.getString("imageHost");
                final String mainImage = contentItem.getString("mainImage");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setId(id);
                itemEntity.setSubtitle(subtitle);
                itemEntity.setImageHost(imageHost);
                itemEntity.setMainImage(mainImage);
                //添加内容
                dataList.add(new SectionHeadEntity(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束

        return dataList;

    }
}
