package com.xiaoxu.xiaoxu_ec.main_delegates.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.ui.recycler.ItemType;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleFields;
import com.xiaoxu.xiaoxu_core.ui.recycler.MultipleItemEntity;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.sort.SortDelegateParent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xiaoxu on 2017/8/28.
 *
 */

public class SortListDelegate extends XiaoXuDelegate {



    @BindView(R2.id.sort_rv_list)
    RecyclerView mRecycleView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_list;
    }

    private void initRecycleView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setItemAnimator(null);


    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecycleView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);



        //添加测试数据
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        int categoryId = 100001;
        String categoryName = "分类";

        for (int i = 0; i < 30; i++) {
            categoryId += i;
            categoryName += i+1;
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ID,categoryId)
                    .setField(MultipleFields.NAME,categoryName)
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.TAG,false)
                    .build();
            dataList.add(entity);
            categoryName=categoryName.substring(0,2);

        }
        //设置第一个分类标签为选中状态
        dataList.get(0).setField(MultipleFields.TAG,true);

        final SortDelegateParent parentDelegate = new SortDelegateParent();
        final SortListRecycleAdapter adapter = new SortListRecycleAdapter(dataList,parentDelegate);
        mRecycleView.setAdapter(adapter);
        //Toast.makeText(getContext()," qingqiu chenggong ",Toast.LENGTH_LONG).show();






       /* RestClient.builder()
                .url("/product/list.do?keyword&categoryId=100003&orderBy=price_desc")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {


                    }
                })
                .build()
                .get();*/


    }
}
