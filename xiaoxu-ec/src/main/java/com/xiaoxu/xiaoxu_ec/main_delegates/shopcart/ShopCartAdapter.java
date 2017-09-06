package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.application.ConfigKeys;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleRecyclerAdapter;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleViewHolder;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.List;

/**
 * Created by xiaoxu on 2017/8/31.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private final ShopCartDelegate  DELEGATE;
    private int mIsSelectedAll = 2;
    private ICartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.00;
    private int mCurrentCount = 0;


    //设置CartItemListener
    public interface ICartItemListener {
        void onItemClick( @Nullable double itemTotalPrice ,boolean allChecked);
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    private static final RequestOptions OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();


    protected ShopCartAdapter(List<MultipleItemEntity> data,ShopCartDelegate delegate) {
        super(data);
        this.DELEGATE = delegate;
        //初始化总价
       /* for (MultipleItemEntity entity : data) {
            final double price = entity.getField(MultipleFields.PRICE);
            final int quantity = entity.getField(MultipleFields.QUANTITY);
            final double total = price * quantity;
            mTotalPrice = mTotalPrice + total;
        }*/
        //添加购物车布局
        addItemType(ItemType.ITEM_SHOP_CART, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(int isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity item) {
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_SHOP_CART:
                //取值
                final int productId = item.getField(MultipleFields.PRODUCT_ID);
                final String image = item.getField(MultipleFields.MAIN_IMAGE);
                final String title = item.getField(MultipleFields.SUBTITLE);
                final String name = item.getField(MultipleFields.NAME);
                final int quantity = item.getField(MultipleFields.QUANTITY);
                final int producChecked = item.getField(MultipleFields.PRODUCT_CHECKED);
                final double price = item.getField(MultipleFields.PRICE);
                final double productTotalPrice =  item.getField(MultipleFields.PRODUCT_TOTAL_PRICE);
                final int productStock =  item.getField(MultipleFields.PRODUCT_STOCK);
                //取控件
                final AppCompatTextView textViewTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView textViewName = holder.getView(R.id.tv_item_shop_cart_name);
                final AppCompatTextView textViewPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView textViewCount = holder.getView(R.id.tv_item_shop_cart_count);


                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_select_shop_cart);

                //赋值
                textViewName.setText(name);
                textViewTitle.setText(title);
                textViewCount.setText(String.valueOf(quantity));
                textViewPrice.setText(String.valueOf(price));

                final AppCompatImageView imageView =
                        holder.getView(R.id.image_item_shop_cart);
                Glide.with(mContext)
                        .load(ConfigureUtil.getConfiguration(ConfigKeys.IMAGE_HOST) + image)
                        .into(imageView);

                switch (mIsSelectedAll){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;

                }



                final int productChecked = item.getField(MultipleFields.PRODUCT_CHECKED);
                //根据数据状态显示左侧勾勾
                if (productChecked == 1) {
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(ConfigureUtil.getApplicationContext(), R.color.app_main));

                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2017/8/31 这里是动态取值，不能取上边isSelected的值
                        final int productChecked = item.getField(MultipleFields.PRODUCT_CHECKED);
                        final int productId = item.getField(MultipleFields.PRODUCT_ID);
                        if (productChecked == 1) {
                            RestClient
                                    .builder()
                                    .loader(mContext)
                                    .url("/cart/un_select.do")
                                    .params("productId", productId)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            if (mCartItemListener != null) {
                                                mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                                                iconIsSelected.setTextColor(Color.GRAY);
                                                item.setField(MultipleFields.PRODUCT_CHECKED, 0);
                                                mCartItemListener.onItemClick(mTotalPrice,false);

                                            }

                                        }
                                    })
                                    .build()
                                    .get();


                        } else {

                            RestClient
                                    .builder()
                                    .loader(mContext)
                                    .url("/cart/select.do")
                                    .params("productId", productId)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            if (mCartItemListener != null) {
                                                mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                                                iconIsSelected.setTextColor
                                                        (ContextCompat.getColor(ConfigureUtil.getApplicationContext(), R.color.app_main));
                                                item.setField(MultipleFields.PRODUCT_CHECKED, 1);
                                                boolean allChecked = JSON.parseObject(response).getJSONObject("data").getBoolean("allChecked");
                                                mCartItemListener.onItemClick(mTotalPrice,allChecked);
                                            }


                                        }
                                    })
                                    .build()
                                    .get();

                        }
                    }
                });

                //添加加减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int productId = item.getField(MultipleFields.PRODUCT_ID);
                        mCurrentCount = Integer.valueOf(textViewCount.getText().toString());
                        if (Integer.parseInt(textViewCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("/cart/update.do")
                                    .loader(mContext)
                                    .params("productId", productId)
                                    .params("count", mCurrentCount - 1)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            if (mCartItemListener != null) {
                                                mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                                                final double itemTotal = (mCurrentCount - 1) * price;
                                                textViewCount.setText(String.valueOf(mCurrentCount - 1));
                                                boolean allChecked = JSON.parseObject(response).getJSONObject("data").getBoolean("allChecked");
                                                mCartItemListener.onItemClick(itemTotal,allChecked);
                                            }
                                        }
                                    })
                                    .build()
                                    .get();
                        }else {
                            Toast.makeText(ConfigureUtil.getApplicationContext(),"再减就木有了",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int productStock = item.getField(MultipleFields.PRODUCT_STOCK);
                        final int productId = item.getField(MultipleFields.PRODUCT_ID);
                        mCurrentCount = Integer.valueOf(textViewCount.getText().toString());
                        if (mCurrentCount < productStock){
                            RestClient.builder()
                                    .url("/cart/update.do")
                                    .loader(mContext)
                                    .params("productId", productId)
                                    .params("count", mCurrentCount + 1)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            XiaoXuLogger.d("plus",response);

                                            if (mCartItemListener != null) {
                                                mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                                                final double itemTotal = (mCurrentCount + 1) * price;
                                                boolean allChecked = JSON.parseObject(response).getJSONObject("data").getBoolean("allChecked");
                                                mCartItemListener.onItemClick(itemTotal,allChecked);
                                                textViewCount.setText(String.valueOf(mCurrentCount + 1));
                                            }
                                        }
                                    })
                                    .build()
                                    .get();
                        } else if(productStock == 0){
                            Toast.makeText(ConfigureUtil.getApplicationContext(),"该商品卖完了！",Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(ConfigureUtil.getApplicationContext(),"达到商品库存上线！",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                break;
            default:
                break;
        }

    }
}
