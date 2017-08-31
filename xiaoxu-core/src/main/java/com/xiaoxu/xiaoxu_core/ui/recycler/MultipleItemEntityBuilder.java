package com.xiaoxu.xiaoxu_core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 */

public class MultipleItemEntityBuilder {


    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();


    public MultipleItemEntityBuilder() {
        //先清除之前的数据,避免数据叠加
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {

        // TODO: 2017/8/30 构建者模式构建完成之后，返回构建的值
        return new MultipleItemEntity(FIELDS);
    }
}
