package com.xiaoxu.xiaoxu_ec.pay;

/**
 * Created by xiaoxu on 2017/8/31.
 * 支付状态监听
 */

public interface IALPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();


}
