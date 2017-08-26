package com.xiaoxu.xiaoxu_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * Container
 */

public final class ItemBuilder {

    private final LinkedHashMap<ItemTabBean, ItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(ItemTabBean bean, ItemDelegate delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<ItemTabBean, ItemDelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<ItemTabBean, ItemDelegate> build() {
        return ITEMS;
    }
}
