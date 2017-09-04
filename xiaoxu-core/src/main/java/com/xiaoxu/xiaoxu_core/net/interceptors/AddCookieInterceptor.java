package com.xiaoxu.xiaoxu_core.net.interceptors;

import com.xiaoxu.xiaoxu_core.application.ConfigKeys;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaoxu on 2017/9/4.
 *
 */

public class AddCookieInterceptor extends BaseInterceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) XiaoXuPreference.getCustomAppProfile(ConfigKeys.COOK_SET.name(),new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            XiaoXuLogger.d("OkHttp", "Adding Header: " + cookie);
        }

        return chain.proceed(builder.build());
    }
}
