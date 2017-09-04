package com.xiaoxu.forworking.event;

/**
 * Created by xiaoxu on 2017/9/4.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.delegates.web.event.Event;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;

public class ShareEvent extends Event {

    @Override
    public String execute(String params) {

        XiaoXuLogger.json("ShareEvent", params);

        final JSONObject object = JSON.parseObject(params).getJSONObject("params");
        final String title = object.getString("title");
        final String url = object.getString("url");
        final String imageUrl = object.getString("imageUrl");
        final String text = object.getString("text");

        /*ShareSDK.initSDK(getContext());
        final OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setTitle(title);
        oks.setText(text);
        oks.setImageUrl(imageUrl);
        oks.setUrl(url);
        oks.show(getContext());*/

        return null;
    }
}

