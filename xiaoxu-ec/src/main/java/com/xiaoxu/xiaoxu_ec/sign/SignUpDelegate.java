package com.xiaoxu.xiaoxu_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.xiaoxu.xiaoxu_core.delegates.XiaoXuDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/25.
 * 注册相关逻辑
 */

public class SignUpDelegate extends XiaoXuDelegate {

    WeakHashMap<String, Object> params = null;

    @BindView(R2.id.edit_sign_up_username)
    TextInputEditText mUserName = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhoneNumber = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_problem)
    TextInputEditText mProblem = null;
    @BindView(R2.id.edit_sign_up_answer)
    TextInputEditText mAnswer = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){

            RestClient
                    .builder()
                    .url("/user/register.do")
                    .params(params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {

                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {

                        }
                    })
                    .build()
                    .post();

            //Toast.makeText(getContext(),"验证通过",Toast.LENGTH_LONG).show();


        }
    }

    private boolean checkForm() {
        final String userName = mUserName.getText().toString();
        final String pasword = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();
        final String phoneNumber = mPhoneNumber.getText().toString();
        final String email = mEmail.getText().toString();
        final String problem = mProblem.getText().toString();
        final String answer = mAnswer.getText().toString();

        boolean isPass = true;

        if (userName.isEmpty()) {
            mUserName.setError(getString(R.string.username_is_empty));
            isPass = false;
        } else if (userName.length() < 6) {
            mUserName.setError("用户名太短");
            isPass = false;
        } else {
            mUserName.setError(null);
        }

        if (pasword.isEmpty()) {
            mPassword.setError("不设置密码怎么登录？");
            isPass = false;
        } else if (pasword.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length()<6 || !rePassword.equals(pasword)) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        if (phoneNumber.isEmpty() || phoneNumber.length()!=11) {
            mPhoneNumber.setError("手机号码错误");
            isPass = false;
        } else {
            mPhoneNumber.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError(getString(R.string.username_is_empty));
            isPass = false;
        } else {
            mEmail.setError(null);
        }


        if (problem.isEmpty()) {
            mProblem.setError("问题不能为空");
            isPass = false;
        } else {
            mProblem.setError(null);
        }

        if (answer.isEmpty()) {
            mAnswer.setError("答案不能为空");
            isPass = false;
        } else {
            mAnswer.setError(null);
        }

        if (isPass){
            params.put("username",userName);
            params.put("password",pasword);
            params.put("email",email);
            params.put("phone",phoneNumber);
            params.put("question",problem);
            params.put("answer",answer);

        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
