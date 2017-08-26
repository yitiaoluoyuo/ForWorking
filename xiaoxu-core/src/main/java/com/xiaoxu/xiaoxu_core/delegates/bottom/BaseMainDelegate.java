package com.xiaoxu.xiaoxu_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.R;
import com.xiaoxu.xiaoxu_core.R2;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public abstract class BaseMainDelegate extends XiaoXuDelegate implements View.OnClickListener {

    //所有子fragment的container
    private final ArrayList<ItemTabBean> ITEM_TAB_BEANS = new ArrayList<>();
    //
    private final ArrayList<ItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<ItemTabBean, ItemDelegate> ITEMS_TAB_AND_DELEGATE = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = Color.RED;

    @BindView(R2.id.tab_main_container_bottom)
    LinearLayoutCompat mTabMainContainer = null;

    public abstract LinkedHashMap<ItemTabBean, ItemDelegate> setItems(ItemBuilder builder);

    @ColorInt
    public abstract int setClickedColor();

    public abstract int setIndexDelegate();




    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS_TAB_AND_DELEGATE.size();


        for (int i = 0; i < size; i++) {
            /*
                    初始化item_main 下部tab的布局
             */
            LayoutInflater.from(getContext()).inflate(R.layout.item_tab_bottom_icon_text_layout, mTabMainContainer);
            final RelativeLayout item = (RelativeLayout) mTabMainContainer.getChildAt(i);
            //设置每个item的点击事件
            // TODO: 2017/8/26 需要创建IDS文件保证点击事件ID的唯一性
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            final ItemTabBean itemTabBean = ITEM_TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(itemTabBean.getIcon());
            itemTitle.setText(itemTabBean.getTitle());
            //如果刚好循环到需要展示的fragment
            if (i == mIndexDelegate) {
                //设置点击后的颜色
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }
        }

         /*
                    初始化item_main 上部fragment的布局
             */
        //-------------------------------------------------------------------------String[] a = {};
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(
                R.id.delegates_main_container,
                mIndexDelegate,
                delegateArray);
    }

    //reset color of the bottom itemBar
    private void resetItemTabColor() {
        final int count = mTabMainContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mTabMainContainer.getChildAt(i);
            final IconTextView itemTabIcon = (IconTextView) item.getChildAt(0);
            itemTabIcon.setTextColor(Color.GRAY);
            final AppCompatTextView itemTabTitle = (AppCompatTextView) item.getChildAt(1);
            itemTabTitle.setTextColor(Color.GRAY);
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<ItemTabBean, ItemDelegate> items = setItems(builder);
        ITEMS_TAB_AND_DELEGATE.putAll(items);
        for (Map.Entry<ItemTabBean, ItemDelegate> item : ITEMS_TAB_AND_DELEGATE.entrySet()) {
            final ItemTabBean key = item.getKey();
            final ItemDelegate value = item.getValue();
            ITEM_TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }


    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetItemTabColor();
        final RelativeLayout itemTab = (RelativeLayout) v;
        final IconTextView itemTabIcon = (IconTextView) itemTab.getChildAt(0);
        itemTabIcon.setTextColor(mClickedColor);
        final AppCompatTextView itemTabTitle = (AppCompatTextView) itemTab.getChildAt(1);
        itemTabTitle.setTextColor(mClickedColor);
        //---------------------------------------需要显示的delegate-------需要隐藏的delegate
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }

}
