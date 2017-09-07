package com.xiaoxu.xiaoxu_ec.main_delegates.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/8/28.
 * 展示分类商品,点击sortList改变ContentDelegate的内容
 * 创建ContentDelegate时需要传入分类ID
 */

public class SortContentDelegate extends LatteDelegate {

    private SectionAdapter mSectionAdapter = null;
    private ArrayList<SectionHeadEntity> mData = null;

    @BindView(R2.id.sort_rv_list_content)
    RecyclerView mRecycleView = null;

    private static final String ARG_CATEGORY_ID = "CATEGORY_ID";
    private int mCategoryId = -1;

    //实例化时就传入categoryId fragment之间传递值非常实用的方法
    //设置CategoryId
    public static final SortContentDelegate newInstance(int categoryId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY_ID, categoryId);
        final SortContentDelegate delegate = new SortContentDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    //接收CategoryId
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mCategoryId = args.getInt(ARG_CATEGORY_ID);
        }
    }

    @Override
    public Object setLayout() {
        return com.xiaoxu.xiaoxu_ec.R.layout.delegate_sort_list_content;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        initData(mCategoryId);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    private void initData(int categoryId) {
        RestClient.builder()
                .url("/product/list.do?keyword&categoryId="+categoryId+"&orderBy=price_desc")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        mData = new SectionDataConverter().convertToEntityList(response);
                        mSectionAdapter = new SectionAdapter
                                (R.layout.item_section_content,R.layout.item_section_header,mData);
                        mRecycleView.setAdapter(mSectionAdapter);

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(ConfigureUtil.getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(ConfigureUtil.getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();


    }
}
