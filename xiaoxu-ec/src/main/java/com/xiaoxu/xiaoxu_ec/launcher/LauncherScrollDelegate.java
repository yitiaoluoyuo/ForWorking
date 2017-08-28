package com.xiaoxu.xiaoxu_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xiaoxu.xiaoxu_core.application.AccountManager;
import com.xiaoxu.xiaoxu_core.application.IUserChecker;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.ui.launcher_scroll.LauncherHolderCreator;
import com.xiaoxu.xiaoxu_core.ui.launcher_scroll.LauncherScrollTag;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;
import com.xiaoxu.xiaoxu_ec.R;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/25.
 * 轮播图
 */

public class LauncherScrollDelegate extends XiaoXuDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher02);
        INTEGERS.add(R.mipmap.launcher03);
        INTEGERS.add(R.mipmap.launcher04);
        INTEGERS.add(R.mipmap.launcher05);
        INTEGERS.add(R.mipmap.launcher06);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                //设置指示点
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                //设置指示点的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                //设置不可循环
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }



    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            INTEGERS.clear();
            XiaoXuPreference.setAppFlag(LauncherScrollTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否已经登录
           AccountManager.checkAccount(new IUserChecker() {
               @Override
               public void onSignIn() {
                   if (mILauncherListener != null){
                       mILauncherListener.onLauncherFinished(LauncherFinishedTag.SIGNED);
                   }
               }

               @Override
               public void onNonSignIn() {
                    mILauncherListener.onLauncherFinished(LauncherFinishedTag.SIGNED_NON);
               }


            });
        }
    }



   /* @Override
    public void onItemClick(int position) {

    }*/
}
