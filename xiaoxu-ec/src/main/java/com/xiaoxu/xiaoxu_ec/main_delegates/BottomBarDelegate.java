package com.xiaoxu.xiaoxu_ec.main_delegates;

import android.graphics.Color;

import com.xiaoxu.xiaoxu_core.delegates.bottom.BaseBottomDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemBuilder;
import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomTabBean;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.main_delegates.discover.DiscoverDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.index.IndexDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.PersonalDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.shopcart.ShopCartDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.SortDelegateParent;

import java.util.LinkedHashMap;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 */

public class BottomBarDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        //本质是用tag绑定tab和delegate（根据tag设置设置tab的颜色和要显示的delegate）
        items.put(new BottomTabBean("{fa-home}", getString(R.string.index)), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegateParent());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
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
