package com.xiaoxu.xiaoxu_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.xiaoxu.xiaoxu_core.R;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by xiaoxu on 2017/8/24.
 * 交互页面的容器
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract XiaoXuDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化视图容器
        initContainer(savedInstanceState);
    }

    //初始化视图容器
    private void initContainer(@Nullable Bundle savedInstanceState){
        ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null){
            //加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            //第一个参数必须为ID文件生成的
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收相关工作
        System.gc();
        System.runFinalization();
    }
}
