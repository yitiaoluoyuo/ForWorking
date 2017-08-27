package com.xiaoxu.xiaoxu_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 */

public class MultipleItemEntity implements MultiItemEntity{

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    //数据容器
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();

    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static ItemMultipleEntityBuilder builder(){
        return new ItemMultipleEntityBuilder();
    }


    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }
}
