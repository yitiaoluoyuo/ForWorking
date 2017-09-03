package com.xiaoxu.xiaoxu_core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.net.callback.IRequest;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.util.file.FileUtilByXiaoXu;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by xiaoxu on 2017/8/25.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    // TODO: 2017/8/25 需要加入失败，错误时的回调
    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtilByXiaoXu.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtilByXiaoXu.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    //如果下载的是apk文件则自动安装
    // TODO: 2017/8/25 需要加入dialog询问是否需要安装 
    private void autoInstallApk(File file) {
        if (FileUtilByXiaoXu.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            ConfigureUtil.getApplicationContext().startActivity(install);
        }
    }
}
