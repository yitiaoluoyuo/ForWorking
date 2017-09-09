package com.xiaoxu.xiaoxu_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by xiaoxu on 2017/8/24.
 * 自定义图标资源
 */

public enum XiaoXuIcons implements Icon {
    icon_scan('\ue603'),
    icon_ali_pay('\ue621'),
    icon_ali_message('\ue624'),
    icon_ali_shop('\ue61c'),
    icon_ali_service('\ue6de'),
    icon_ali_star('\ue69f'),
    icon_ali_shop_fill('\ue69f'),

    ;

    private char character;

    XiaoXuIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        //中划线代替下划线
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
