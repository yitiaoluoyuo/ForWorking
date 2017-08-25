package com.xiaoxu.xiaoxu_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.xiaoxu.xiaoxu_core.application.XiaoXu;

/**
 * Created by xiaoxu on 2017/8/25.
 *
 */

public class DimenUtil {

    // TODO: 2017/8/25 测量屏幕宽高的方法
    public static int getScreenWidth(){
        final Resources resources = XiaoXu.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = XiaoXu.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
