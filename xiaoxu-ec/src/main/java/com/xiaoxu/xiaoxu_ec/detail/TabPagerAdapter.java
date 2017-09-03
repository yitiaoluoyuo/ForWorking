package com.xiaoxu.xiaoxu_ec.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoxu on 2017/9/3.
 */
//FragmentStatePagerAdapter 不会保存每一个pager的状态，页面销毁后，数据也随之销毁。适合商品详情的应用场景
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURES = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm,JSONObject data) {
        super(fm);
        //获取tabs信息，注意，这里的tabs是一条信息
       /* final JSONArray tabs = data.getJSONArray("tabs");
        final int size = tabs.size();
        for (int i = 0; i < size; i++) {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            //存储每个图片
            final int pictureSize = pictureUrls.size();
            for (int j = 0; j < pictureSize; j++) {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURES.add(eachTabPicturesArray);
*/
            //自定义数据
            final String imageHost = "http://img.happymmall.com/";
            final String subImages = data.getString("subImages");
            String[] subImageArray = subImages.split(",");


            final String mainImage = data.getString("mainImage");
            final List<String> images = new ArrayList<>();
            //先把主图加进去
            images.add(imageHost+mainImage);
            final int size = subImageArray.length;
            for (int i = 0; i < size; i++) {
                images.add(imageHost+subImageArray[i]);
            }

        for (int i = 0; i <3 ; i++) {
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            int size02= images.size();
            for (int j = 0; j <size02 ; j++) {
                eachTabPicturesArray.add(images.get(j));
            }

            PICTURES.add(eachTabPicturesArray);
        }
        TAB_TITLES.add("详情");
        TAB_TITLES.add("评价");
        TAB_TITLES.add("推荐");



        }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ImageDelegate.create(PICTURES.get(0));
        } else if (position == 1) {
            return ImageDelegate.create(PICTURES.get(1));
        }else if (position == 2) {
            return ImageDelegate.create(PICTURES.get(2));
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
