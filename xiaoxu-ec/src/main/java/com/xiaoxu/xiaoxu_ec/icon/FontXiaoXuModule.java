package com.xiaoxu.xiaoxu_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by xiaoxu on 2017/8/24.
 * 传入自己需要的字体图标资源
 */

public class FontXiaoXuModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return XiaoXuIcons.values();
    }
}
