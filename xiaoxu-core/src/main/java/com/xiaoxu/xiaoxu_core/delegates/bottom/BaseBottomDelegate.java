package com.xiaoxu.xiaoxu_core.delegates.bottom;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.R;
import com.xiaoxu.xiaoxu_core.R2;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public abstract class BaseBottomDelegate extends MainDelegate implements View.OnClickListener {

    
    private final ArrayList<BottomTabBean> BOTTOM_TAB_BEANS = new ArrayList<>();
    //所有子fragment的container
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    //
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS_TAB_AND_DELEGATE = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0;
    private int mClickedColor = R.color.app_ui;

    @BindView(R2.id.bottom_tab_container)
    LinearLayoutCompat mTabContainer = null;

    // TODO: 2017/8/30 抽象方法一般是为了强制赋值 
    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    //
    @ColorInt
    public abstract int setClickedColor();
    //设置默认展示页面
    public abstract int setIndexDelegate();




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        // TODO: 2017/8/30 赋值思想 用builder.addItems().build(); 返回  LinkedHashMap<BottomTabBean, BottomItemDelegate>
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS_TAB_AND_DELEGATE.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS_TAB_AND_DELEGATE.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            BOTTOM_TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }




    @Override
    public Object setLayout() {

        return R.layout.delegate_tabs_pages;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS_TAB_AND_DELEGATE.size();
        for (int i = 0; i < size; i++) {
            /*
                    初始化item_main 下部tab的布局
             */
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_icon_text, mTabContainer);
            final RelativeLayout item = (RelativeLayout) mTabContainer.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            // TODO: 2017/8/30 分别获取单个tab中的 IconTextView和 AppCompatTextView
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);

            final BottomTabBean itemTabBean = BOTTOM_TAB_BEANS.get(i);

            //初始化tab数据
            itemIcon.setText(itemTabBean.getIcon());
            itemTitle.setText(itemTabBean.getTitle());

            //如果刚好循环到需要展示的fragment
            if (i == mIndexDelegate) {
                //设置点击后的颜色
                itemIcon.setTextColor(ContextCompat.getColor(getContext(),R.color.app_ui));
                itemTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.app_ui));
            }
        }

         /*
                    初始化item_main 上部fragment的布局
             */
        //-------------------------------------------------------------------------String[] a = {};
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);

        /**
         *  loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments)
         */
        getSupportDelegate().loadMultipleRootFragment(
                R.id.bottom_tab_delegate_container, mIndexDelegate, delegateArray);
    }

    //reset color of the bottom itemBar
    private void resetItemTabColor() {
        final int count = mTabContainer.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mTabContainer.getChildAt(i);
            final IconTextView itemTabIcon = (IconTextView) item.getChildAt(0);
            //Color.GRAY
            itemTabIcon.setTextColor(ContextCompat.getColor(getContext(),R.color.app_gray_03));
            final AppCompatTextView itemTabTitle = (AppCompatTextView) item.getChildAt(1);
            itemTabTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.app_gray_03));
        }
    }






    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetItemTabColor();
        final RelativeLayout itemTab = (RelativeLayout) v;
        final IconTextView itemTabIcon = (IconTextView) itemTab.getChildAt(0);
        itemTabIcon.setTextColor(ContextCompat.getColor(getContext(),R.color.app_ui));
        final AppCompatTextView itemTabTitle = (AppCompatTextView) itemTab.getChildAt(1);
        itemTabTitle.setTextColor(ContextCompat.getColor(getContext(),R.color.app_ui));
        //---------------------------------------需要显示的delegate-------需要隐藏的delegate
        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        //注意先后顺序
        mCurrentDelegate = tag;
    }

}
