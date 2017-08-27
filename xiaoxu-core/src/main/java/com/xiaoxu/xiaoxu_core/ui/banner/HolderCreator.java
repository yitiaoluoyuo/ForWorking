package com.xiaoxu.xiaoxu_core.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by xiaoxu on 2017/8/27.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
