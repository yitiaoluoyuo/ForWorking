package com.xiaoxu.xiaoxu_core.util.callback;

/**
 * Created by xiaoxu on 2017/9/2.
 */

public interface IGlobalCallback<T> {

    // TODO: 2017/9/2 需要掌握的接口泛型设计方法
    void executeCallback(T tags);

}
