package com.xiaoxu.xiaoxu_core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 */

public class ItemMultipleEntityBuilder {


    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();


    public ItemMultipleEntityBuilder() {
        //先清除之前的数据,避免数据叠加
        FIELDS.clear();
    }

    public final ItemMultipleEntityBuilder setItemType(int itemType) {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final ItemMultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final ItemMultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {

        return new MultipleItemEntity(FIELDS);
    }
}
