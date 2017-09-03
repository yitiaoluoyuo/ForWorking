package com.xiaoxu.xiaoxu_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;

/**
 * Created by xiaoxu on 2017/8/25.
 *
 */

public class DimenUtil {

    // TODO: 2017/8/25 测量屏幕宽高的方法
    public static int getScreenWidth(){
        final Resources resources = ConfigureUtil.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = ConfigureUtil.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
