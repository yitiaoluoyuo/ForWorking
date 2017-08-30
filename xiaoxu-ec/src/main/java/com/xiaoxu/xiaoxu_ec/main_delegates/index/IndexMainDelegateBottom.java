package com.xiaoxu.xiaoxu_ec.main_delegates.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.bottom.BottomItemDelegate;
import com.xiaoxu.xiaoxu_core.ui.recycler.BaseDividerItemDecoration;
import com.xiaoxu.xiaoxu_core.ui.refresh.RefreshHandler;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.BottomBarDelegate;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class IndexMainDelegateBottom extends BottomItemDelegate {

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

        /**
         * RecyclerAdapter steep  02
         *
         * 绑定视图 ？？？
         */
        mRefreshHandler = RefreshHandler
                .create(mRefreshLayout,mRecyclerView,new IndexDataConverter());

    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    /**
     * RecycleView steep 1
     *      初始化RecycleView
     */
    private void initRecycleView(){
        /**
         * RecycleView steep 2
         *      设置布局管理器
         */
        //设置布局管理器**网格布局，**********************************把屏幕分为4列
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(
                BaseDividerItemDecoration.create(
                        ContextCompat.getColor(getContext(),R.color.colorGrayLight)
                        ,7));
        //为父容器添加点击事件，避免bottomBar不跳转的坑
        final BottomBarDelegate bottomBarDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(bottomBarDelegate));
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecycleView();

        /**
         * RecycleView steep 3
         *      获取数据源
         */
        mRefreshHandler.firstPage("/product/list.do?keyword&categoryId=100001&orderBy=price_desc");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
