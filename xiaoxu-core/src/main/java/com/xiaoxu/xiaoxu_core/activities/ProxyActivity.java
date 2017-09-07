package com.xiaoxu.xiaoxu_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;

import com.xiaoxu.xiaoxu_core.R;
import com.xiaoxu.xiaoxu_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by xiaoxu on 2017/8/24.
 * 交互页面的容器
 */

public abstract class ProxyActivity extends AppCompatActivity implements ISupportActivity {

    private final SupportActivityDelegate ACTIVITY_DELEGATE = new SupportActivityDelegate(this);

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ACTIVITY_DELEGATE.onCreate(savedInstanceState);
        ACTIVITY_DELEGATE.showFragmentStackHierarchyView();
        ACTIVITY_DELEGATE.logFragmentStackHierarchy("SupportActivityDelegate");

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
            ACTIVITY_DELEGATE.loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        ACTIVITY_DELEGATE.onDestroy();
        super.onDestroy();
        //垃圾回收相关工作
        System.gc();
        System.runFinalization();
    }



    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return ACTIVITY_DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return ACTIVITY_DELEGATE.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return ACTIVITY_DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        ACTIVITY_DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return ACTIVITY_DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        ACTIVITY_DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        ACTIVITY_DELEGATE.onBackPressed();
    }


}
