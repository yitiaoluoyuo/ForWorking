package com.xiaoxu.xiaoxu_core.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxu.xiaoxu_core.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 * 适配多布局
 */
// TODO: 2017/8/27 不传入泛型时强制实现onBindView方法的原理？

public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
    implements BaseQuickAdapter.SpanSizeLookup,OnItemClickListener{


    //确保初始化一次Banner，防止重复Item加载
    private boolean mIsInitBanner = false;

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        // 设置不同的item布局
        init();
    }

    //工厂模式处理
    private static MultipleRecyclerAdapter create(List<MultipleItemEntity> data){
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter){
        return new MultipleRecyclerAdapter(converter.convert());
    }

    //设置不同的item布局
    private void init(){
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        //设置宽度的监听
        setSpanSizeLookup(this);
        //打开加载的动画效果
        openLoadAnimation();
        //设置多此执行动画
        isFirstOnly(false);
    }



    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.crate(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {

        final int id = item.getField(MultipleFields.ID);
        final int categoryId = item.getField(MultipleFields.CATEGORY_ID);
        final String name;
        final String subtitle;
        final String mainImage;
        final float price = item.getField(MultipleFields.PRICE);
        final int status = item.getField(MultipleFields.STATUS);
        final String imageHost;

        switch (holder.getItemViewType()){
            case ItemType.TEXT:
                name = item.getField(MultipleFields.NAME);
                holder.setText(R.id.item_tv_single,name);
                break;
            case ItemType.IMAGE:
                imageHost = item.getField(MultipleFields.IMAGE_HOST);
                mainImage = item.getField(MultipleFields.MAIN_IMAGE);
                Glide.with(mContext)
                        .load(imageHost+mainImage)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.item_img_multiple));
                break;
            case ItemType.TEXT_IMAGE:
                imageHost = item.getField(MultipleFields.IMAGE_HOST);
                mainImage = item.getField(MultipleFields.MAIN_IMAGE);
                subtitle = item.getField(MultipleFields.SUBTITLE);
                Glide.with(mContext)
                        .load(imageHost+mainImage)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.item_img_multiple));
                holder.setText(R.id.item_tv_multiple,subtitle);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    //需要传入图片ListArray<string> urls 资源
                    /*bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    //设置初始化成功
                    mIsInitBanner = true;*/
                }
                break;
            default:
                break;
        }



    }

    @Override
    public void onItemClick(int position) {

    }
}
