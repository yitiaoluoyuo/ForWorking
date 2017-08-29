package com.xiaoxu.xiaoxu_ec.main_delegates;

import android.graphics.Color;

import com.xiaoxu.xiaoxu_core.delegates.bottom.BaseMainDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemBuilder;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemMainDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemTabBean;
import com.xiaoxu.xiaoxu_ec.main_delegates.find.FindMainDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.index.IndexMainDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.mine.MineMainDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.shopcart.ShopCartMainDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 */

public class BottomBarDelegate extends BaseMainDelegate {

    @Override
    public LinkedHashMap<ItemTabBean, ItemMainDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<ItemTabBean, ItemMainDelegate> itemsTabAndDelegate = new LinkedHashMap<>();
        itemsTabAndDelegate.put(new ItemTabBean("{fa-home}", "主页"), new IndexMainDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-sort}", "分类"), new SortDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-compass}", "发现"), new FindMainDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-shopping-cart}", "购物车"), new ShopCartMainDelegate());
        itemsTabAndDelegate.put(new ItemTabBean("{fa-user}", "我的"), new MineMainDelegate());
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
