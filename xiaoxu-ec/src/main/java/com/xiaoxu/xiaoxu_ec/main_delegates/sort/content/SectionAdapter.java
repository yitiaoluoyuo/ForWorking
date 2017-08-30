package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/8/30.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionHeadEntity,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionHeadEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, SectionHeadEntity item) {
        holder.setText(R.id.header,item.header);
        holder.setVisible(R.id.more,item.isMore());
        holder.addOnClickListener(R.id.more);


    }

    @Override
    protected void convert(BaseViewHolder helper, SectionHeadEntity item) {

    }
}
