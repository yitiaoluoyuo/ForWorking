package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/8/30.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionHeadEntity,BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionHeadEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder holder, SectionHeadEntity item) {
        holder.setText(R.id.tv_header,item.header);
        holder.setVisible(R.id.tv_more,item.isMore());
        holder.addOnClickListener(R.id.tv_more);


    }

    @Override
    protected void convert(BaseViewHolder helper, SectionHeadEntity item) {
        //item.t返回SectionBean类型（SectionContentItemEntity）
        int id = item.t.getId();
        String subTitle = item.t.getSubtitle();
        String imageHost = item.t.getImageHost();
        String mainImage = item.t.getMainImage();
        helper.setText(R.id.tv_title,subTitle);
        final AppCompatImageView imageView = helper.getView(R.id.iv_section);
        Glide.with(mContext)
                .load(imageHost+mainImage)
                .apply(OPTIONS)
                .into(imageView);
    }
}
