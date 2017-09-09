package com.xiaoxu.xiaoxu_ec.main_delegates.shopcart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.address.AddAddressDelegate;
import com.xiaoxu.xiaoxu_ec.pay.IALPayResultListener;
import com.xiaoxu.xiaoxu_ec.pay.textpay.WeChatPayDelegate;
import com.xiaoxu.xiaoxu_ec.sign.SignInDelegate;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class ShopCartDelegate extends BottomItemDelegate
        implements ISuccess, ShopCartAdapter.ICartItemListener, IALPayResultListener {

    private double mTotalPrice = 0.00;
    private ShopCartAdapter mAdapter = null;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.tv_shop_cart_no_item)
    AppCompatTextView mTvNoItem = null;
    /* @BindView(R2.id.stub_no_item)
     ViewStubCompat mStubNoItem = null;*/
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTvTotalPrice = null;
    private JSONArray mCartDataJsonArray;


    /**
     * 全选/取消全选购物车商品
     */
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll() {
        // TODO: 2017/8/31 巧妙的利用View的tag
        final int tag = (int) mIconSelectAll.getTag();
        if (tag == 0) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_ui));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(1);
            RestClient.builder()
                    .url("/cart/select_all.do")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
                            XiaoXuLogger.d("checkAll", "/cart/select_all.do" + response);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            XiaoXuLogger.d("checkAll", "select_all.do ERROR" + msg);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            XiaoXuLogger.d("checkAll", "elect_all.do onFailure");
                        }
                    })
                    .build()
                    .get();
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(0);
            RestClient.builder()
                    .url("/cart/un_select_all.do")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
                            XiaoXuLogger.d("checkAll", "/un_select_all.do" + response);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            XiaoXuLogger.d("checkAll", "/un_select_all.do ERROR" + msg);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            XiaoXuLogger.d("checkAll", "/un_select_all.do onFailure");
                        }
                    })
                    .build()
                    .get();
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
        checkItemCount();

    }


    /**
     * 删除购物车中被选中的商品
     */
    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem() {
        final List<MultipleItemEntity> totalEntity = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        //要删除的商品id
        StringBuilder ids = new StringBuilder();

        for (MultipleItemEntity entity : totalEntity) {
            final int productChecked = entity.getField(MultipleFields.PRODUCT_CHECKED);
            if (productChecked == 1) {
                deleteEntities.add(entity);
                ids.append(String.valueOf(entity.getField(MultipleFields.PRODUCT_ID))).append(",");
            }
        }
        int deleteEntityListCount = deleteEntities.size();
        for (int i = 0; i < deleteEntityListCount; i++) {
            final int currentPosition = deleteEntities.get(i).getField(MultipleFields.POSITION);

            if (currentPosition < mAdapter.getData().size()) {
                mAdapter.remove(currentPosition);
                //删除后，此时currentPosition是删除前currentPosition的下一个商品
                for (int j = currentPosition; j < totalEntity.size(); j++) {

                    int a = totalEntity.get(j).getField(MultipleFields.POSITION);
                    totalEntity.get(j).setField(MultipleFields.POSITION, a - 1);
                }
            }
        }
        //判断是否有被选中的商品
        if (ids.length() != 0) {
            //数据库删除商品
            RestClient.builder()
                    .url("/cart/delete_product.do")
                    .params("productIds", ids.substring(0, ids.length() - 1))
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
                            Toast.makeText(getContext(), "商品删除成功", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .get();
            checkItemCount();
        } else {
            Toast.makeText(getContext(), "还木有选择要删除的商品", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 清空购物车
     */
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        final List<MultipleItemEntity> totalEntity = mAdapter.getData();
        //要删除的商品id
        StringBuilder ids = new StringBuilder();

        //遍历添加全部商品ID
        for (MultipleItemEntity entity : totalEntity) {
            ids.append(String.valueOf(entity.getField(MultipleFields.PRODUCT_ID))).append(",");
        }
        if (ids.length() != 0) {
            //数据库删除商品
            RestClient.builder()
                    .url("/cart/delete_product.do")
                    .params("productIds", ids.substring(0, ids.length() - 1))
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
                            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
                            mIconSelectAll.setTextColor(Color.GRAY);
                            Toast.makeText(getContext(), "商品删除成功", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .get();
            mAdapter.getData().clear();
            mAdapter.notifyDataSetChanged();
            checkItemCount();
        } else {
            Toast.makeText(getContext(), "购物车已经是空的了", Toast.LENGTH_SHORT).show();
        }


    }


    @OnClick(R2.id.tv_shop_cart_pay)
    void onClickPay() {
        //createOrder();
        RestClient.builder()
                .url("/shipping/list.do")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONArray array = JSON.parseObject(response).getJSONObject("data").getJSONArray("list");
                        if (array.size() == 0) {
                            Toast.makeText(getContext(), "您还没有添加收货地址", Toast.LENGTH_LONG).show();
                            getParentDelegate().getSupportDelegate().start(new AddAddressDelegate());
                        } else {
                            if (mCartDataJsonArray.size() == 0) {
                                Toast.makeText(getContext(), "请添加商品到购物车", Toast.LENGTH_LONG).show();
                            } else {
                                getParentDelegate().getSupportDelegate().start(new WeChatPayDelegate());
                            }
                        }
                    }
                })
                .build()
                .get();


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
                       /* FastPay.create(ShopCartDelegate.this,getSupportDelegate())
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(0123)
                                .beginPayDialog();*/
                    }
                })
                .build()
                .get();
    }

    void request() {
        //请求数据
        RestClient.builder()
                .url("/cart/list.do")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            request();
        }
    }

    @Override
    public void onSuccess(String response) {
        XiaoXuLogger.d("cart", response);
        int status = JSON.parseObject(response).getInteger("status");
        if (status == 0) {
            XiaoXuLogger.d("check", response);
            //出事化全选状态 allChecked
            boolean allChecked = JSON.parseObject(response).getJSONObject("data").getBoolean("allChecked");
            if (allChecked) {
                mIconSelectAll.setTag(1);
                mIconSelectAll.setTextColor
                        (ContextCompat.getColor(getContext(), R.color.app_ui));
            } else {
                mIconSelectAll.setTag(0);
                mIconSelectAll.setTextColor(Color.GRAY);
            }

            mCartDataJsonArray = JSON.parseObject(response).getJSONObject("data").getJSONArray("cartProductVoList");
            mTotalPrice = JSON.parseObject(response).getJSONObject("data").getDouble("cartTotalPrice");
            //转换数据
            final ArrayList<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convertToEntityList();

            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAdapter = new ShopCartAdapter(data, new ShopCartDelegate());
            mAdapter.setCartItemListener(this);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mAdapter);
            mTvTotalPrice.setText(String.valueOf(mTotalPrice));
            checkItemCount();
        } else {
            Toast.makeText(getContext(), "需要重新登录", Toast.LENGTH_LONG).show();
            getParentDelegate().getSupportDelegate().startWithPop(new SignInDelegate());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();

        if (count == 0) {
            mTvNoItem.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            // mStubNoItem.setVisibility(View.INVISIBLE);
            mTvNoItem.setVisibility(View.GONE);

        }


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

    @Override
    public void onItemClick(@Nullable double totalPrice, boolean allChecked) {
        mTvTotalPrice.setText(String.valueOf(totalPrice));
        if (allChecked) {
            mIconSelectAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_ui));
        } else {
            mIconSelectAll.setTextColor(Color.GRAY);
        }
    }
}
