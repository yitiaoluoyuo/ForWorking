package com.xiaoxu.xiaoxu_core.delegates;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public abstract class XiaoXuDelegate extends PermissionCheckerDelegate {


    @SuppressWarnings("unchecked")
    public <T extends XiaoXuDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
