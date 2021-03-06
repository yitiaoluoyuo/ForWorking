package com.xiaoxu.xiaoxu_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.xiaoxu.xiaoxu_core.application.AccountManager;
import com.xiaoxu.xiaoxu_core.application.IUserChecker;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_core.ui.launcher_scroll.LauncherScrollTag;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;
import com.xiaoxu.xiaoxu_core.util.timer.BaseTimerTask;
import com.xiaoxu.xiaoxu_core.util.timer.ITimerListener;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/25.
 * 启动倒计时
 */

public class LauncherDelegate extends MainDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

   //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        //判断是否是第一次运行APP
        if (!XiaoXuPreference.getAppFlag(LauncherScrollTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        //登录成功后的回调处理
                        mILauncherListener.onLauncherFinished(LauncherFinishedTag.SIGNED);
                    }
                }

                @Override
                public void onNonSignIn() {
                    if (mILauncherListener != null) {
                        //没有登录做的回调处理
                        mILauncherListener.onLauncherFinished(LauncherFinishedTag.SIGNED_NON);
                    }
                }

            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
