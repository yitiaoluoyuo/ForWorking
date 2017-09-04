package com.xiaoxu.xiaoxu_core.delegates.web.event;

import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;

/**
 * Created by xiaoxu on 2017/9/4.
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        XiaoXuLogger.e("UndefineEvent", params);
        return null;
    }
}
