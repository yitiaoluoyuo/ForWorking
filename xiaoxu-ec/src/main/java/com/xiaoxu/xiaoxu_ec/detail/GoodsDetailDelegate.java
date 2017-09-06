package com.xiaoxu.xiaoxu_ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.joanzapata.iconify.widget.IconTextView;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.ui.banner.HolderCreator;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by xiaoxu on 2017/8/28.
 */

public class GoodsDetailDelegate extends XiaoXuDelegate  implements
        AppBarLayout.OnOffsetChangedListener{

    public static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickAddShopCart(){
        RestClient.builder()
                .url("/cart/add.do")
                .params("productId",mGoodsId)
                .params("count",1)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),"商品添加购物车成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }


    public static GoodsDetailDelegate create(@NonNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null){
            mGoodsId = args.getInt(ARG_GOODS_ID);
            Toast.makeText(getContext(),"mGoodsId  :"+ mGoodsId,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        initData();
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor
                (ContextCompat.getColor(getContext(), R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initData() {
        RestClient.builder()
                .url("/product/detail.do")
                .params("productId", mGoodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject  data =
                                JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        //setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private void initBanner(JSONObject data) {
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
        mBanner
                .setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //设置水平打开的动画
        return new DefaultHorizontalAnimator();
        //super.onCreateFragmentAnimator()
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
