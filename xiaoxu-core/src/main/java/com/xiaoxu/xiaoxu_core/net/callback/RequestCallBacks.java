package com.xiaoxu.xiaoxu_core.net.callback;

import android.os.Handler;

import com.xiaoxu.xiaoxu_core.application.ConfigKeys;
import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.ui.loader.LoaderStyle;
import com.xiaoxu.xiaoxu_core.ui.loader.XiaoXuLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoxu on 2017/8/24.
 */

public class RequestCallBacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderstyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE = loaderstyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                // TODO: 2017/8/24 把空指针扼杀在摇篮
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();


    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    //为loader加3秒的延迟
    private  void stopLoading(){
        Object delayedTimeValue = ConfigureUtil.getConfiguration(ConfigKeys.LOADER_DELAYED);
        if (delayedTimeValue == null){
            delayedTimeValue = 1000;
        }
        if (LOADERSTYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    XiaoXuLoader.stopLoading();
                }
            },Long.valueOf(String.valueOf(delayedTimeValue)));
        }
    }
}
