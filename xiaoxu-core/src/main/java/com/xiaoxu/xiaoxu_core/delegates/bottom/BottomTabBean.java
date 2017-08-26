package com.xiaoxu.xiaoxu_core.delegates.bottom;

/**
 * Created by xiaoxu on 2017/8/26.
 *
 * for contain tabs behind of the main View
 */

public final class BottomTabBean {

    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
