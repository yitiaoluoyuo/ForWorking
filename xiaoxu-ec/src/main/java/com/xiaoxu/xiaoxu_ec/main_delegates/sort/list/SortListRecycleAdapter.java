package com.xiaoxu.xiaoxu_ec.main_delegates.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleRecyclerAdapter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleViewHolder;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.SortDelegateParent;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.content.SortContentDelegate;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by xiaoxu on 2017/8/28.
 *
 */

public class SortListRecycleAdapter extends MultipleRecyclerAdapter {

            private final SortDelegateParent SORT_DELEGATE_PARENT;

    private int mPrePosition = 0;

    // TODO: 2017/8/28 此处深入理解继承的奥妙 （*基础*）
    protected SortListRecycleAdapter(List<MultipleItemEntity> data, SortDelegateParent delegate) {
        super(data);
        this.SORT_DELEGATE_PARENT = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_sort_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        //super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String name = entity.getField(MultipleFields.NAME);
                final int categoryId = entity.getField(MultipleFields.ID);
                final boolean isClick = entity.getField(MultipleFields.TAG);
                final AppCompatTextView textView = holder.getView(R.id.sort_tv_list_name);
                final View line = holder.getView(R.id.sort_view_line);
                final View itemView = holder.itemView;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击事件处理
                        int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);

                            //更新选中item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                            final int categoryId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(categoryId);

                        }
                    }
                });

                if (!isClick) {
                    line.setVisibility(View.INVISIBLE);
                    // TODO: 2017/8/29  ContextCompat 选取颜色能兼容更多的手机
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_gray));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorGrayLight));
                } else {
                    line.setVisibility(View.VISIBLE);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.sort_tv_list_name, name);
                break;
            default:
                break;
        }
    }


    private void showContent(int categoryId) {
        final SortContentDelegate delegateNew = SortContentDelegate.newInstance(categoryId);
        switchContent(delegateNew);
    }

    private void switchContent(SortContentDelegate delegateNew) {

        final XiaoXuDelegate contentDelegate =
                SupportHelper.findFragment(SORT_DELEGATE_PARENT.getChildFragmentManager(), SortContentDelegate.class);

        if (delegateNew.isAdded()){
            if (contentDelegate != null) {

                contentDelegate.getSupportDelegate().replaceFragment(delegateNew, false);//是否加入回退栈
            }

        }else {
            if (contentDelegate != null) {
                contentDelegate.getSupportDelegate().loadRootFragment(R.id.sort_rv_list_content,delegateNew);

                contentDelegate.getSupportDelegate().replaceFragment(delegateNew, false);//是否加入回退栈
            }
        }

    }



}
