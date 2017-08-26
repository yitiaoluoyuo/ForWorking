package com.xiaoxu.xiaoxu_ec.sign;

import com.alibaba.fastjson.JSONObject;
import com.xiaoxu.xiaoxu_ec.database.UserProfile;

/**
 * Created by xiaoxu on 2017/8/26.
 * sign helper
 */

public class SignStatueHandler {

    public static UserProfile onSignInSuccess(JSONObject responseJsonData, ISignSuccessListener signSuccessListener) {

        final JSONObject profileJson = responseJsonData.getJSONObject("data");
        final long id = profileJson.getLong("id");
        final String username = profileJson.getString("username");
        final String email = profileJson.getString("email");
        final String phone = profileJson.getString("phone");
        final int role = profileJson.getInteger("role");
        final long createTime = profileJson.getLong("createTime");
        final long updateTime = profileJson.getLong("createTime");

        final UserProfile profile = new UserProfile(id, username, email, phone, role,createTime,updateTime);

        //把UserProfile中的数据插入数据库
        //DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功了
        //AccountManager.setSignInState(true);

        //在projectActivity中实现的接口，处理业务
        // （页面跳转，计时）
        signSuccessListener.onSignInSuccess();

        return profile;
    }


    public static void onSignUpSuccess(String response, ISignSuccessListener signListener) {

       /* final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        //把返回的数据插入到数据库
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);*/

        //已经注册并登录成功了
        //AccountManager.setSignInState(true);
        //注册成功后的回调处理
        //signListener.onSignUpSuccess();
    }
}
