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
import com.xiaoxu.xiaoxu_ec.main_delegates.index.inside.MessageDelegate;
import com.xiaoxu.xiaoxu_ec.main_delegates.index.inside.ScanDelegate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/26.
 */

public class IndexDelegate extends BottomItemDelegate {

    private String url =null;

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

   @OnClick(R2.id.icon_index_scan)
    void onClickScanQrCode() {
       getParentDelegate().getSupportDelegate().start(new ScanDelegate());
    }

    @OnClick(R2.id.icon_index_message)
    void onClickMessage() {
        getParentDelegate().getSupportDelegate().start(new MessageDelegate());
    }



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

    //设置下拉刷新的loader
    private void initRefreshProgress() {
        mRefreshLayout.setColorSchemeResources(
                R.color.app_ui,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView(){
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
        initRefreshProgress();
        initRecycleView();
        /**
         * RecycleView steep 3
         *      获取数据源
         */
        //为关键字搜索预留的方法
        createUrl("测");
        mRefreshHandler.firstPage(url);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


    String createUrl(String  key){
        if (key==null|key.length()==0){
            url ="/product/list.do?keyword=测&pageSize=80";
        }else {
            url = "/product/list.do?keyword="+key+"&pageSize=80";
        }
      return url;
    }

}
