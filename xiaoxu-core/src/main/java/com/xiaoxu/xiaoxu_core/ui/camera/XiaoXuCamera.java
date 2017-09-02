package com.xiaoxu.xiaoxu_core.ui.camera;

import android.net.Uri;

import com.xiaoxu.xiaoxu_core.delegates.PermissionCheckerDelegate;
import com.xiaoxu.xiaoxu_core.util.file.FileUtilByXiaoXu;

/**
 * Created by xiaoxu on 2017/9/2.
 * 相机调用类
 */

public class XiaoXuCamera {

    public static Uri createCropFile() {
        return Uri.parse(
                FileUtilByXiaoXu.createFile(
                        "crop_image", FileUtilByXiaoXu.getFileNameByTime("IMG", "jpg")
                ).getPath()
        );
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }

}
