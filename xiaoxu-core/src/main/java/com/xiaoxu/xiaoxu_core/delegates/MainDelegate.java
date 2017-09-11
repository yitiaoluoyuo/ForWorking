package com.xiaoxu.xiaoxu_core.delegates;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public abstract class MainDelegate extends PermissionCheckerDelegate {


    @SuppressWarnings("unchecked")
    public <T extends MainDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
