package com.xiaoxu.xiaoxu_ec.main_delegates.sort.list;

import com.xiaoxu.xiaoxu_core.ui.recycler.DataConverter;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/28.
 */

public class SortListDataConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convertToEntityList() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        /**
         *  服务器获取的数据
         */
       /* final JSONArray dataArray = JSON
                .parseObject(getJsonData())
                .getJSONObject("data")
                .getJSONArray("list");*/

       /* final int size =dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int categoryId = data.getInteger("");
            final String categoryName = data.getString("");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ID,categoryId)
                    .setField(MultipleFields.NAME,categoryName)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);
        }*/


        /**
         *  测试数据
         */


        int categoryId = 100001;
        int categoryName = 1;

        for (int i = 0; i < 5; i++) {
            categoryId += i;
            categoryName += i;
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ID,categoryId)
                    .setField(MultipleFields.NAME,categoryName)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);

        }
        //设置第一个分类标签为选中状态
        dataList.get(0).setField(MultipleFields.TAG,true);
        return dataList;
    }
}
