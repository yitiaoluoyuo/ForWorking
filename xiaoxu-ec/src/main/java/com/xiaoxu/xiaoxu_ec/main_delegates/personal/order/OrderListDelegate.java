package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.PersonalDelegate;
import com.xiaoxu.xiaoxu_ec.sign.SignInDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/9/1.
 */

public class OrderListDelegate extends LatteDelegate {

    private OrderSectionAdapter mAdapter = null;

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.tv_order_no_item)
    AppCompatTextView mTvNoItem = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    void request() {
        RestClient.builder()
                .loader(getContext())
                .url("/order/list.do")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        int status = JSON.parseObject(response).getInteger("status");
                        if (status == 1 |status ==10) {
                            Toast.makeText(getContext(), "需要重新登录", Toast.LENGTH_LONG).show();
                            getParentDelegate().getSupportDelegate().startWithPop(new SignInDelegate());
                        }
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<OrderSectionEntity> data =
                                new OrderSectionDataConverter().convertToEntityList(response);
                        mAdapter = new OrderSectionAdapter(R.layout.item_section_order_list, R.layout.item_section_order_header, data);
                        mRecyclerView.setAdapter(mAdapter);
                        checkItemCount();
                        //mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderListDelegate.this));
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示

        } else {// 重新显示到最前端中
            request();
        }
    }

    @SuppressWarnings("RestrictedApi")
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();

        if (count == 0) {
            mTvNoItem.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTvNoItem.setVisibility(View.GONE);

        }


    }
}
