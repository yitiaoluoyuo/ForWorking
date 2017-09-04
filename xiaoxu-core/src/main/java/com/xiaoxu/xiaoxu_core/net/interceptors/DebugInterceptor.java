package com.xiaoxu.xiaoxu_core.net.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.xiaoxu.xiaoxu_core.util.file.FileUtilByXiaoXu;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xiaoxu on 2017/8/25.
 * 封装OKHTP3中的interceptor
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    //raw文件夹中的文件，系统自动生成ID
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")//数据格式为json
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtilByXiaoXu.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //读取raw目录中的文件,并返回为字符串
        final String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
