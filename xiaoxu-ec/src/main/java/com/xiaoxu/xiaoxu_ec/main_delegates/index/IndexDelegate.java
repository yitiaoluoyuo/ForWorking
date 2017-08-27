package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.bottom.ItemDelegate;
import com.xiaoxu.xiaoxu_core.ui.refresh.RefreshHandler;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class IndexDelegate extends ItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

   /* @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
        startScanWithCheck(this.getParentDelegate());
    }*/


    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler
                .create(mRefreshLayout,mRecyclerView,new IndexDataConverter());
        //设置图片加载策略
        /*final RequestOptions RECYCLER_OPTIONS =
                new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate();

        Glide.with(getContext())
                .load("http://img.happymmall.com/173335a4-5dce-4afd-9f18-a10623724c4e.jpeg")
                .apply(RECYCLER_OPTIONS)
                .into((ImageView) MultipleViewHolder.crate(mRecyclerView).getView(com.xiaoxu.xiaoxu_core.R.id.item_img_single));*/

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView(){
        //设置为网格布局，******************************************把屏幕分为4列
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);

       /* mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));*/
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecycleView();
        mRefreshHandler.firstPage("/product/list.do?keyword&categoryId=100001&orderBy=price_desc");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
