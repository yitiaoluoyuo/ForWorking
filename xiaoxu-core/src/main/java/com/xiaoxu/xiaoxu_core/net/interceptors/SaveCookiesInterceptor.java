package com.xiaoxu.xiaoxu_core.net.interceptors;

import com.xiaoxu.xiaoxu_core.application.ConfigKeys;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Response;

/**
 * Created by xiaoxu on 2017/9/4.
 */

public class SaveCookiesInterceptor extends BaseInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            XiaoXuPreference.addCustomAppProfile(ConfigKeys.COOK_SET.name(), cookies);
        }
        return originalResponse;
    }
}
