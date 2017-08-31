package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleRecyclerAdapter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleViewHolder;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/8/31.
 *
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加购物车布局
        addItemType(ItemType.ITEM_SHOP_CART, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        switch (holder.getItemViewType()){
            case ItemType.ITEM_SHOP_CART:
                //取值
                final String image = item.getField(MultipleFields.MAIN_IMAGE);
                final String title = item.getField(MultipleFields.SUBTITLE);
                final String name = item.getField(MultipleFields.NAME);
                final int quantity = item.getField(MultipleFields.QUANTITY);
                final double price = item.getField(MultipleFields.PRICE);
                //取控件
                final AppCompatTextView textViewTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView textViewName = holder.getView(R.id.tv_item_shop_cart_name);
                final AppCompatTextView textViewPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView textViewCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                //赋值
                textViewName.setText(name);
                textViewTitle.setText(title);
                textViewCount.setText(String.valueOf(quantity));
                textViewPrice.setText(String.valueOf(price));

                final AppCompatImageView imageView =
                        holder.getView(R.id.image_item_shop_cart);
                Glide.with(mContext)
                        .load("http://img.happymmall.com/"+image)
                        .into(imageView);

                break;
            default:
                break;
        }

    }
}
