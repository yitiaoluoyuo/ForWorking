package com.xiaoxu.xiaoxu_ec.sign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.delegates.MainDelegate;
import com.xiaoxu.xiaoxu_core.net.RestClient;
import com.xiaoxu.xiaoxu_core.net.callback.IError;
import com.xiaoxu.xiaoxu_core.net.callback.IFailure;
import com.xiaoxu.xiaoxu_core.net.callback.ISuccess;
import com.xiaoxu.xiaoxu_ec.R;
import com.xiaoxu.xiaoxu_ec.R2;
import com.xiaoxu.xiaoxu_ec.main_delegates.BottomBarDelegate;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoxu on 2017/8/25.
 * 注册相关逻辑
 */

public class SignUpDelegate extends MainDelegate {

    private WeakHashMap<String, Object> params = new WeakHashMap<>();

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
    private String userName;
    private String password;
    private String rePassword;
    private String phoneNumber;
    private String email;
    private String problem;
    private String answer;

    private ISignSuccessListener mSignSuccessListener;



    // TODO: 2017/8/26 ??????   sign in  need
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (_mActivity instanceof ISignSuccessListener){
            mSignSuccessListener = (ISignSuccessListener) _mActivity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if (checkForm()){

            params.put("username", userName);
            params.put("password", password);
            params.put("email", email);
            params.put("phone", phoneNumber);
            params.put("question", problem);
            params.put("answer", answer);

           RestClient
                    .builder()
                    .url("/user/register.do")
                    .params(params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            JSONObject jsonData = JSON.parseObject(response);
                            //通过服务器返回的状态码对注册状态进行判断并处理
                            final int statusCode = jsonData.getInteger("status");
                            final String msg = jsonData.getString("msg");

                            if (statusCode == 0){
                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                                SignStatueHandler.onSignUpSuccess(mSignSuccessListener);
                            }else if (statusCode == 1){
                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            Toast.makeText(getContext(),"sign up error",Toast.LENGTH_LONG).show();
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(),"sign up failure",Toast.LENGTH_LONG).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
    }

    @OnClick(R2.id.icon_sign_up_back)
    void onClickBack(){
        getSupportDelegate().start(new BottomBarDelegate());
    }

    private boolean checkForm() {
        userName = mUserName.getText().toString();
        password = mPassword.getText().toString();
        rePassword = mRePassword.getText().toString();
        phoneNumber = mPhoneNumber.getText().toString();
        email = mEmail.getText().toString();
        problem = mProblem.getText().toString();
        answer = mAnswer.getText().toString();

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
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || !rePassword.equals(password)) {
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
            mEmail.setError("邮箱格式不正确");
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
