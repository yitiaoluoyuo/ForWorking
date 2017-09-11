package com.xiaoxu.xiaoxu_ec.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.application.ConfigureUtil;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.pay.textpay.AliPayDelegate;
import com.xiaoxu.xiaoxu_ec.pay.textpay.WeChatPayDelegate;

import me.yokeyword.fragmentation.SupportFragmentDelegate;

/**
 * Created by xiaoxu on 2017/8/31.
 */

public class FastPay implements View.OnClickListener{

    private SupportFragmentDelegate supportFragmentDelegate = null;
    //设置支付回调监听
    private IALPayResultListener mIALPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private FastPay(MainDelegate delegate, SupportFragmentDelegate supportFragmentDelegate) {
        this.supportFragmentDelegate = supportFragmentDelegate;
        this.mActivity = delegate.getProxyActivity();
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static FastPay create(MainDelegate delegate, SupportFragmentDelegate supportFragmentDelegate) {

        return new FastPay(delegate,supportFragmentDelegate);
    }

    public void beginPayDialog() {
        // TODO: 2017/8/31 dialog 的基本用法
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_pane);
            //底部居中
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_dialog_pay_alpay).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(this);
            window.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(this);
        }
    }

    public FastPay setPayResultListener(IALPayResultListener listener) {
        this.mIALPayResultListener = listener;
        return this;
    }

    public FastPay setOrderId(int orderId) {
        this.mOrderID = orderId;
        return this;
    }

    private void alPay(int orderId) {
        final String singUrl = "你的服务端支付地址" + orderId;
        //获取签名字符串
        RestClient.builder()
                .url("HTTPS://QR.ALIPAY.COM/FKX03091M5YHG2LERT2E80")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       /* final String paySign = JSON.parseObject(response).getString("result");
                        XiaoXuLogger.d("PAY_SIGN", paySign);
                        //必须是异步的调用客户端支付接口
                        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity, mIALPayResultListener);
                        //多线程同时支付
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);*/
                        Toast.makeText(ConfigureUtil.getApplicationContext(),response+"pay finished",Toast.LENGTH_LONG).show();
                    }
                })
                .build()
                .get();
    }

    /*private void weChatPay(int orderId) {
        LatteLoader.stopLoading();
        final String weChatPrePayUrl = "你的服务端微信预支付地址" + orderId;
        LatteLogger.d("WX_PAY", weChatPrePayUrl);

        final IWXAPI iwxapi = LatteWeChat.getInstance().getWXAPI();
        final String appId = Latte.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
        iwxapi.registerApp(appId);
        RestClient.builder()
                .url(weChatPrePayUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject result =
                                JSON.parseObject(response).getJSONObject("result");
                        final String prepayId = result.getString("prepayid");
                        final String partnerId = result.getString("partnerid");
                        final String packageValue = result.getString("package");
                        final String timestamp = result.getString("timestamp");
                        final String nonceStr = result.getString("noncestr");
                        final String paySign = result.getString("sign");

                        final PayReq payReq = new PayReq();
                        payReq.appId = appId;
                        payReq.prepayId = prepayId;
                        payReq.partnerId = partnerId;
                        payReq.packageValue = packageValue;
                        payReq.timeStamp = timestamp;
                        payReq.nonceStr = nonceStr;
                        payReq.sign = paySign;

                        iwxapi.sendReq(payReq);
                    }
                })
                .build()
                .post();
    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();
        // TODO: 2017/8/31 library 不能使用switch case
        if (id == R.id.btn_dialog_pay_alpay) {
           // alPay(mOrderID);
            supportFragmentDelegate.start(new AliPayDelegate());
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_wechat) {
            //weChatPay(mOrderID);
            supportFragmentDelegate.start(new WeChatPayDelegate());
            mDialog.cancel();
        } else if (id == R.id.btn_dialog_pay_cancel) {
            mDialog.cancel();
        }
    }


}
