package com.xiaoxu.xiaoxu_core.delegates;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {


    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
