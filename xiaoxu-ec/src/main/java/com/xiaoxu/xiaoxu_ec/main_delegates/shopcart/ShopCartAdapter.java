package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.application.XiaoXu;
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

    private boolean mIsSelectedAll = false;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;

    //设置CartItemListener
    public interface ICartItemListener {
        void onItemClick(double itemTotalPrice);
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(MultipleFields.PRICE);
            final int quantity = entity.getField(MultipleFields.QUANTITY);
            final double total = price * quantity;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车布局
        addItemType(ItemType.ITEM_SHOP_CART, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder,final MultipleItemEntity item) {
        switch (holder.getItemViewType()){
            case ItemType.ITEM_SHOP_CART:
                //取值
                final String image = item.getField(MultipleFields.MAIN_IMAGE);
                final int title = item.getField(MultipleFields.SUBTITLE);
                final String name = item.getField(MultipleFields.NAME);
                final int quantity = item.getField(MultipleFields.QUANTITY);
                final double price = item.getField(MultipleFields.PRICE);
                //取控件
                final AppCompatTextView textViewTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView textViewName = holder.getView(R.id.tv_item_shop_cart_name);
                final AppCompatTextView textViewPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView textViewCount = holder.getView(R.id.tv_item_shop_cart_count);
                final AppCompatTextView textViewTotalPrice = holder.getView(R.id.tv_shop_cart_total_price);

                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_select_shop_cart);

                //赋值
                textViewName.setText(name);
                textViewTitle.setText(String.valueOf(title));
                textViewCount.setText(String.valueOf(quantity));
                textViewPrice.setText(String.valueOf(price));

                final AppCompatImageView imageView =
                        holder.getView(R.id.image_item_shop_cart);
                Glide.with(mContext)
                        .load("http://img.happymmall.com/"+image)
                        .into(imageView);
                //在左侧勾勾渲染之前查看全选与否的状态
                item.setField(MultipleFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = item.getField(MultipleFields.IS_SELECTED);
                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(XiaoXu.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2017/8/31 这里是动态取值，不能取上边isSelected的值
                        final boolean currentSelected = item.getField(MultipleFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            item.setField(MultipleFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(XiaoXu.getApplicationContext(), R.color.app_main));
                            item.setField(MultipleFields.IS_SELECTED, true);
                        }
                    }
                });




                break;
            default:
                break;
        }

    }
}
