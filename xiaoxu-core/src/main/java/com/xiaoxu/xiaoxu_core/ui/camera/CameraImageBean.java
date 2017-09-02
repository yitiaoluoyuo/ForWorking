package com.xiaoxu.xiaoxu_core.ui.camera;

import android.net.Uri;

/**
 * Created by xiaoxu on 2017/9/2.
 * 存储中间值
 */

public final class CameraImageBean {

    private Uri mPath = null;

    private CameraImageBean(){

    }

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
