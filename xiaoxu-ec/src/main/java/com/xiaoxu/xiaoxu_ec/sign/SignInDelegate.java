package com.xiaoxu.xiaoxu_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_core.util.logger.XiaoXuLogger;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/26.
 * 登录业务
 */

public class SignInDelegate extends XiaoXuDelegate {

    public static final String TAG = "tag";
    private WeakHashMap<String, Object> params = new WeakHashMap<>();

    @BindView(R2.id.edit_sign_in_username)
    TextInputEditText mUserName = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
    private String userName;
    private String password;

    private ISignSuccessListener mSignSuccessListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignSuccessListener){
            mSignSuccessListener = (ISignSuccessListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {

            params.put("username", userName);
            params.put("password", password);
            RestClient
                    .builder()
                    .url("/user/login.do")
                    .params(params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            JSONObject responseJsonData = JSON.parseObject(response);
                            final int statusCode = responseJsonData.getInteger("status");

                            //通过服务器返回的状态码对注册状态进行判断并处理
                            if (statusCode == 0) {
                                //UserProfile userProfile = SignStatueHandler.onSignInSuccess(responseJsonData, mSignSuccessListener);

                                /*Toast.makeText(getContext(), "欢迎 " +
                                        userProfile.getUserName()+
                                        " 访问", Toast.LENGTH_SHORT).show();*/
                                SignStatueHandler.onSignInSuccess(responseJsonData,mSignSuccessListener);

                                XiaoXuLogger.json("/user/login.do",response);
                            } else if (statusCode == 1) {

                                Toast.makeText(getContext(), (String) responseJsonData.get("msg"), Toast.LENGTH_LONG).show();
                            }

                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(), "sign in error", Toast.LENGTH_LONG).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(), "sign in failure", Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();


        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        userName = mUserName.getText().toString();
        password = mPassword.getText().toString();

        boolean isPass = true;

        if (userName.isEmpty()) {
            mUserName.setError(getString(R.string.username_is_empty));
            isPass = false;
        } else {
            mUserName.setError(null);
        }

        if (password.isEmpty()) {
            mPassword.setError("不设置密码怎么登录？");
            isPass = false;
        } else if (password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }


}
