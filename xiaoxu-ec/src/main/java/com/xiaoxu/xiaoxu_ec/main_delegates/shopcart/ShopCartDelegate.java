package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.pay.FastPay;
import com.xiaoxu.xiaoxu_ec.pay.IALPayResultListener;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class ShopCartDelegate extends BottomItemDelegate
        implements ISuccess ,ShopCartAdapter.ICartItemListener,IALPayResultListener{

    private ShopCartAdapter mAdapter = null;
    //
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;


    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        // TODO: 2017/8/31 巧妙的利用View的tag
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
        checkItemCount();
    }


    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> data = mAdapter.getData();
        //mTotalCount = data.size();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity entity : data) {
            final boolean isSelected = entity.getField(MultipleFields.IS_SELECTED);
            if (isSelected) {
                deleteEntities.add(entity);
            }
        }
        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(MultipleFields.POSITION);
            if (entityPosition > mCurrentCount - 1) {
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            } else {
                removePosition = entityPosition;
            }
            if (removePosition <= mAdapter.getItemCount()) {
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition, mAdapter.getItemCount());
                // mAdapter.notifyDataSetChanged();
            }
        }
        checkItemCount();
    }


    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        checkItemCount();
    }


    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        createOrder();
    }

    //创建订单，注意，和支付是没有关系的
    private void createOrder() {
        final String orderUrl = "/product/list.do?keyword&categoryId=100004&orderBy=price_desc";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加参数
        RestClient.builder()
                .url(orderUrl)
                .loader(getContext())
                //.params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        XiaoXuLogger.d("ORDER", response);
                       // final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(0123)
                                .beginPayDialog();
                    }
                })
                .build()
                .get();

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //请求数据
        RestClient.builder()
                .url("/cart/list.do")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {

        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
        //转换数据
       /* final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convertToEntityList();*/

        //
        ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final int productId = 1;
        final int quantity = 3;
        final String productName = "绝味小龙虾1kg 麻辣/十三香口味(17-25只)";
        final String productSubtitle = ",";
        final String productMainImage = "dbf30c59-2178-4257-a22c-a03704c32863.png";
        final double productPrice = 60.253;

        for (int i = 0; i < 20; i++) {
            MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.MAIN_IMAGE, productMainImage)
                    .setField(MultipleFields.SUBTITLE, i)
                    .setField(MultipleFields.NAME, productName)
                    .setField(MultipleFields.QUANTITY, quantity)
                    .setField(MultipleFields.PRICE, productPrice)
                    .setField(MultipleFields.PRODUCT_ID, productId)
                    .setField(MultipleFields.ITEM_TYPE, 6)
                    .setField(MultipleFields.IS_SELECTED, false)
                    .setField(MultipleFields.POSITION, i)
                    .build();
            dataList.add(entity);
        }


        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mAdapter = new ShopCartAdapter(dataList);

        mAdapter.setCartItemListener(this);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));


        checkItemCount();
    }


    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            // TODO: 2017/8/31 API
            final View stubView = mStubNoItem.inflate();
            final AppCompatTextView tvToBuy =
                    (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "你该购物啦！", Toast.LENGTH_SHORT).show();
                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(double itemTotalPrice) {

        mTvTotalPrice.setText(String.valueOf(mAdapter.getTotalPrice()));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
