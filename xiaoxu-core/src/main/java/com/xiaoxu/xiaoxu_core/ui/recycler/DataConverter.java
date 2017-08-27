package com.xiaoxu.xiaoxu_core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/8/27.
 * 数据的转换处理 entity的一个基类
 */

public abstract class DataConverter {

    //设置只有子类可以引用的权限,子类中实现了抽象方法给 ENTITIES_LIST赋值，才能调用，防止产生异常。
    protected final ArrayList<MultipleItemEntity> ENTITIES_LIST = new ArrayList<>();
    private String mJsonData = null;

    //约束子类必须给 ENTITIES_LIST 赋值
    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData(){
        ENTITIES_LIST.clear();
    }
}
