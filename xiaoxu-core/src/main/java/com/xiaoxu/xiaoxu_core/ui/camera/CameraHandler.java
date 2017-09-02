package com.xiaoxu.xiaoxu_core.ui.camera;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;
import com.xiaoxu.xiaoxu_core.R;
import com.xiaoxu.xiaoxu_core.delegates.PermissionCheckerDelegate;
import com.xiaoxu.xiaoxu_core.util.file.FileUtilByXiaoXu;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;

import java.io.File;

/**
 * Created by xiaoxu on 2017/9/2.
 * 照片处理类
 */

public class CameraHandler implements View.OnClickListener {



    private final AlertDialog DIALOG;
    private final PermissionCheckerDelegate DELEGATE;

    public CameraHandler(PermissionCheckerDelegate delegate) {
        this.DELEGATE = delegate;
        DIALOG = new AlertDialog.Builder(delegate.getContext()).create();
    }

    final void beginCameraDialog() {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);

            window.findViewById(R.id.photodialog_btn_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_btn_native).setOnClickListener(this);
        }
    }

    private String getPhotoName() {
        return FileUtilByXiaoXu.getFileNameByTime("IMG", "jpg");
    }

    private void takePhoto() {
        final String currentPhotoName = getPhotoName();
        Uri CONTENT_URI = null;
        File tempFile = null;

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //利用工具类创建相册文件
        //判断SD卡是否挂载
        if (TextUtils.equals(Environment.getExternalStorageState(),Environment.MEDIA_MOUNTED)){
             tempFile = new File(FileUtilByXiaoXu.CAMERA_PHOTO_DIR, currentPhotoName);
             CONTENT_URI=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            XiaoXuLogger.d("tempFile   sd:"+tempFile+",    CONTENT_URI  sd:"+ CONTENT_URI);
        }else{
            //The content:// style URI for the internal storage.
            CONTENT_URI=MediaStore.Images.Media.INTERNAL_CONTENT_URI;
            @SuppressLint("SdCardPath")
            String a = "/data/user/0/com.xiaoxu.forworking.example/files";
            tempFile = new File(a, currentPhotoName);
            XiaoXuLogger.d("tempFile  in :"+tempFile+",   CONTENT_URI  in:"+CONTENT_URI);
        }


        //兼容7.0及以上的写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());
            final Uri uri = DELEGATE.getContext().getContentResolver().
                    insert(CONTENT_URI, contentValues);
            //需要将Uri路径转化为实际路径
            final File realFile =
                    FileUtils.getFileByPath(FileUtilByXiaoXu.getRealFilePath(DELEGATE.getContext(), uri));
            XiaoXuLogger.d("realFile :"+realFile+",   CONTENT_URI:"+CONTENT_URI);
            final Uri realUri = Uri.fromFile(realFile);

            CameraImageBean.getInstance().setPath(realUri);
            // TODO: 2017/9/2
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        } else {
            final Uri fileUri = Uri.fromFile(tempFile);
            CameraImageBean.getInstance().setPath(fileUri);
            String name= MediaStore.EXTRA_OUTPUT;
            intent.putExtra(name, fileUri);
        }
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKE_PHOTO);
    }

    private void pickPhoto() {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult
                (Intent.createChooser(intent, "选择获取图片的方式***"), RequestCodes.PICK_PHOTO);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.photodialog_btn_cancel) {
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_take) {
            takePhoto();
            DIALOG.cancel();
        } else if (id == R.id.photodialog_btn_native) {
            pickPhoto();
            DIALOG.cancel();
        }
    }

}
