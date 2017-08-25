package com.xiaoxu.xiaoxu_core.ui.launcher_scroll;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by xiaoxu on 2017/8/25.
 *  获取启动轮播图的ImageView
 */

public class LauncherHolder implements Holder<Integer> {

    AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
