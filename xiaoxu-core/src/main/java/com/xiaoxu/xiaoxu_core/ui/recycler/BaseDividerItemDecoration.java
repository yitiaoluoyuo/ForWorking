package com.xiaoxu.xiaoxu_core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by xiaoxu on 2017/8/28.
 *
 */

public class BaseDividerItemDecoration extends DividerItemDecoration {

    private BaseDividerItemDecoration(@ColorInt int intColor, int size){
        setDividerLookup( new DividerLookupImpl(intColor,size));
    }

    public static BaseDividerItemDecoration create(@ColorInt int intColor, int size){
        return new BaseDividerItemDecoration(intColor,size);
    }

}
