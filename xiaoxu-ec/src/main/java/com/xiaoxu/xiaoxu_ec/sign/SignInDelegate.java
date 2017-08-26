package com.xiaoxu.xiaoxu_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

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

    private WeakHashMap<String, Object> params = new WeakHashMap<>();

    @BindView(R2.id.edit_sign_in_username)
    TextInputEditText mUserName = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;
    private String userName;
    private String password;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignUp(){
        if (checkForm()){

            params.put("username", userName);
            params.put("password", password);
            RestClient
                    .builder()
                    .url("/user/login.do")
                    .params(params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                            XiaoXuLogger.json("/user/login.do",response);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();

            //Toast.makeText(getContext(),"验证通过"+userName,Toast.LENGTH_LONG).show();



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
