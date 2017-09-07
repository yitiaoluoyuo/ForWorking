package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order;


import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class OrderSectionAdapter extends BaseSectionQuickAdapter<OrderSectionEntity,BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();



    public OrderSectionAdapter(int layoutResId, int sectionHeadResId, List<OrderSectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);

    }

    @Override
    protected void convertHead(BaseViewHolder helper, OrderSectionEntity item) {
        helper.setVisible(R.id.tv_order_list_is_more,true);
        helper.setText(R.id.tv_order_list_receiver,"收件人："+item.getReceiverName());
        helper.setText(R.id.tv_order_list_statusDesc,"订单状态："+item.getStatusDesc());
        helper.setText(R.id.tv_order_list_payment,"总价：￥"+String.valueOf(item.getPayment()));
        helper.setText(R.id.tv_order_list_order_number,"订单号："+String.valueOf(item.getOrderNo()));
        helper.setText(R.id.tv_order_list_create_time,"创建时间："+String.valueOf(item.getCreateTime()));

    }

    @Override
    protected void convert(BaseViewHolder helper, OrderSectionEntity item) {
        int id = item.t.getProductId();
         String mProductName = item.t.getProductName();
         String mProductImage = item.t.getProductImage();
         double mCurrentUnitPrice = item.t.getCurrentUnitPrice();
         int mQuantity = item.t.getQuantity();
         double mTotalPrice = item.t.getTotalPrice();

        helper.setText(R.id.tv_order_list_name,mProductName.substring(8,mProductName.length()-1));
        helper.setText(R.id.tv_order_list_price,"单价："+String.valueOf(mCurrentUnitPrice));
        helper.setText(R.id.tv_order_list_t_price,"小计：￥"+String.valueOf(mTotalPrice));
        helper.setText(R.id.tv_order_list_count,"数量："+String.valueOf(mQuantity));

        AppCompatImageView imageView = helper.getView(R.id.image_order_list);
        Glide.with(mContext)
                .load(ConfigureUtil.getImageHost()+mProductImage)
                .apply(OPTIONS)
                .into(imageView);


    }
}
