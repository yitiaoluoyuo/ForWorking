package com.xiaoxu.xiaoxu_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * Container
 */

public final class ItemBuilder {

    private final LinkedHashMap<ItemTabBean, ItemMainDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(ItemTabBean bean, ItemMainDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<ItemTabBean, ItemMainDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<ItemTabBean, ItemMainDelegate> build() {
        return ITEMS;
    }
}
