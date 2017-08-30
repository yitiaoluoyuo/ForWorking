package com.xiaoxu.xiaoxu_core.delegates.bottom;

import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * tabPage
 *
 * 添加双击backKey退出事件
 */

public abstract class BottomItemDelegate extends XiaoXuDelegate {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {

        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            //Toast.makeText(_mActivity, "双击退出" + XiaoXu.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            Toast.makeText(_mActivity, "双击退出" , Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}
