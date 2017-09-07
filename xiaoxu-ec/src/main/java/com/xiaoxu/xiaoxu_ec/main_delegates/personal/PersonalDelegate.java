package com.xiaoxu.xiaoxu_ec.main_delegates.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.List.ListAdapter;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.List.ListBean;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.order.OrderListDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.address.AddressDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.order.afterMarket.AfterMarketDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.profile.UserProfileDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.personal.settings.SettingsDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class PersonalDelegate extends BottomItemDelegate {

    @BindView(R2.id.img_user_avatar)
    CircleImageView circleImageView = null;

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRvSettings = null;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_mine;
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_to_pay)
    void onClickToPay() {
        mArgs.putString(ORDER_TYPE, "pay");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_to_receive)
    void onClickToReceiver() {
        mArgs.putString(ORDER_TYPE, "receive");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_to_evaluate)
    void onClickToEvaluate() {
        mArgs.putString(ORDER_TYPE, "evaluate");
        startOrderListByType();
    }

    @OnClick(R2.id.ll_after_market)
    void onClickAfterMarket() {
        mArgs.putString(ORDER_TYPE, "after");
        final AfterMarketDelegate delegate= new AfterMarketDelegate();
        getParentDelegate().getSupportDelegate().start(delegate);
    }



    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar() {
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }

    public void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final ListBean address = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setDelegate(new SettingsDelegate())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
       mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }

    @Override
    public void onResume() {
        super.onResume();
       /*CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        XiaoXuLogger.d("ON_CROP", args);
                        Glide.with(getContext())
                                .load(args)
                                .into(circleImageView);
                    }

                });*/
    }
}
