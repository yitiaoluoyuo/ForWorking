package com.xiaoxu.xiaoxu_core.ui.launcher_scroll;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by xiaoxu on 2017/8/25.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder>{

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
