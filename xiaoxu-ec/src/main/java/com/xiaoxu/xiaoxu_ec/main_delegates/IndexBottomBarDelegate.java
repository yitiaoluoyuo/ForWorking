package com.xiaoxu.xiaoxu_ec.main_delegates;

import android.graphics.Color;

import com.xiaoxu.xiaoxu_core.delegates.bottom.BaseMainDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemBuilder;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemTabBean;
import com.xiaoxu.xiaoxu_ec.main_delegates.find.FindDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.index.IndexDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.mine.MineDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.shopcart.ShopCartDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 */

public class IndexBottomBarDelegate extends BaseMainDelegate {

    @Override
    public LinkedHashMap<ItemTabBean, ItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<ItemTabBean, ItemDelegate> itemsTabAndDelegate = new LinkedHashMap<>();
        itemsTabAndDelegate.put(new ItemTabBean("{fa-home}", "主页"), new IndexDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-sort}", "分类"), new SortDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-compass}", "发现"), new FindDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-user}", "我的"), new MineDelegate());
        return builder.addItems(itemsTabAndDelegate).build();
    }


    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
