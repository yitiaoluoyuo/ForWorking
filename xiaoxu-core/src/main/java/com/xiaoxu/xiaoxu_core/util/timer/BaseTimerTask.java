package com.xiaoxu.xiaoxu_core.util.timer;

import java.util.TimerTask;

/**
 * Created by xiaoxu on 2017/8/25.
 *计时器
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }

    }
}
