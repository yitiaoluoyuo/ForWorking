package com.xiaoxu.xiaoxu_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by xiaoxu on 2017/8/24.
 * 集中处理根fragment里的业务
 */

public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder;

    public abstract Object setLayout();
    public abstract void onBinderView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout()instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }

        if (rootView != null){
            mUnbinder = ButterKnife.bind(this,rootView);
            onBinderView(savedInstanceState,rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
