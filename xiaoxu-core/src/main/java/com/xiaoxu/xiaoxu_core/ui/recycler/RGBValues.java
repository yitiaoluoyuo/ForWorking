package com.xiaoxu.xiaoxu_core.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * Created by xiaoxu on 2017/8/28.
 */

@AutoValue
public abstract class RGBValues {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RGBValues create(int red, int green, int blue) {
        return new AutoValue_RGBValues(red,green,blue);
    }


}
