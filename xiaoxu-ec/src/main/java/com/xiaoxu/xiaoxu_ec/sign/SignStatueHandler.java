package com.xiaoxu.xiaoxu_ec.sign;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_core.application.AccountManager;
import com.xiaoxu.xiaoxu_core.util.storage.XiaoXuPreference;
import com.xiaoxu.xiaoxu_ec.database.DatabaseManager;
import com.xiaoxu.xiaoxu_ec.database.UserBean;

/**
 * Created by xiaoxu on 2017/8/26.
 * sign helper
 */

public class SignStatueHandler {

    public static UserBean onSignInSuccess(JSONObject responseJsonData, ISignSuccessListener signSuccessListener) {

        final JSONObject profileJson = responseJsonData.getJSONObject("data");
        final long id = profileJson.getLong("id");
        final String username = profileJson.getString("username");
        final String email = profileJson.getString("email");
        final String phone = profileJson.getString("phone");
        final int role = profileJson.getInteger("role");
        final long createTime = profileJson.getLong("createTime");
        final long updateTime = profileJson.getLong("createTime");

        XiaoXuPreference.addCustomAppProfile("id",String.valueOf(id));

        final UserBean userBean = new UserBean(id, username, email, phone, role,createTime,updateTime);

        //把UserProfile中的数据插入数据库
        DatabaseManager.getInstance().getDao().insert(userBean);

        //登录成功,存储登录状态
        AccountManager.setSignInState(true);

        //在projectActivity中实现的接口，处理业务
        // （页面跳转，计时）
        signSuccessListener.onSignInSuccess();

        return userBean;
    }


    public static void onSignUpSuccess( ISignSuccessListener signListener) {

        //已经注册并没有登录
        AccountManager.setSignInState(false);
        //注册成功后的回调处理
        signListener.onSignUpSuccess();
    }
}
