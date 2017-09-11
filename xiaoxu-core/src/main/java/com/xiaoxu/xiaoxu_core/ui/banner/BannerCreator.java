package com.xiaoxu.xiaoxu_core.ui.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.xiaoxu.xiaoxu_core.R;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/27.
 *
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {

        //banner实现
        convenientBanner
                .setPages(new HolderCreator(), banners)//设置viewHolder和URLList
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})//设置 导航点
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //.setOnItemClickListener(clickListener)//监听item点击
                .setPageTransformer(new DefaultTransformer())//自定义翻页动画效果
                .startTurning(3000)
                .setCanLoop(true);

    }

   /* @Override
    public View createView(Context context) {
        return null;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {

    }*/
}
